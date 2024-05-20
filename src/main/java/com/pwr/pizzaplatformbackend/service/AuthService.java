package com.pwr.pizzaplatformbackend.service;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import com.pwr.pizzaplatformbackend.security.AppUserRole;
import com.pwr.pizzaplatformbackend.security.LoginRequest;
import com.pwr.pizzaplatformbackend.security.LoginResponse;
import com.pwr.pizzaplatformbackend.security.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final AWSCognitoIdentityProvider cognitoClient;

    @Value("${user.pool.id}")
    private String userPoolId;

    @Value("${spring.security.oauth2.client.registration.cognito.clientId}")
    private String clientId;

    public void register(RegistrationRequest request) {
        try {
            List<AttributeType> userAttributes = new ArrayList<>();
            String username = request.firstname();
            userAttributes.add(new AttributeType().withName("email").withValue(request.email()));
            userAttributes.add(new AttributeType().withName("profile").withValue(String.valueOf(request.userRole())));

            AdminCreateUserRequest createUserRequest = new AdminCreateUserRequest()
                    .withUserPoolId(userPoolId)
                    .withUsername(username)
                    .withUserAttributes(userAttributes)
                    .withDesiredDeliveryMediums(DeliveryMediumType.EMAIL);

            cognitoClient.adminCreateUser(createUserRequest);

        } catch (Exception e) {
            throw new RuntimeException("Error registering user: " + e.getMessage(), e);
        }
    }

    public LoginResponse login(LoginRequest loginRequest) {
        AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest()
                .withUserPoolId(userPoolId)
                .withClientId(clientId)
                .withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                .withAuthParameters(new HashMap<>() {{
                    put("USERNAME", loginRequest.email());
                    put("PASSWORD", loginRequest.password());
                }});

        AdminInitiateAuthResult authResponse = cognitoClient.adminInitiateAuth(authRequest);
        if (authResponse.getChallengeName() != null) {
            if (authResponse.getChallengeName().equals("NEW_PASSWORD_REQUIRED")) {// Prepare challenge response
                HashMap<String, String> params = new HashMap<>();
                params.put("USERNAME", loginRequest.email());
                params.put("NEW_PASSWORD", loginRequest.password());

                AdminRespondToAuthChallengeRequest challengeResponse = new AdminRespondToAuthChallengeRequest()
                        .withChallengeName(ChallengeNameType.NEW_PASSWORD_REQUIRED)
                        .withClientId(clientId)
                        .withUserPoolId(userPoolId)
                        .withChallengeResponses(params)
                        .withSession(authResponse.getSession());

                AdminRespondToAuthChallengeResult authResponse2 = cognitoClient.adminRespondToAuthChallenge(challengeResponse);
                return new LoginResponse(
                        authResponse2.getAuthenticationResult().getAccessToken(),
                        AppUserRole.USER
                );
            }
        }
        return new LoginResponse(authResponse.getAuthenticationResult().getAccessToken(), AppUserRole.USER);
    }
}
