resource "aws_cognito_user_pool" "user_pool" {
  name                     = "cognito-user-pool"
  alias_attributes         = ["email"]
  // alias that cen be use for user identification, so we can use username or emial to sign
  auto_verified_attributes = ["email"] // cognito sends verification email when user being created

  email_configuration {
    email_sending_account = "COGNITO_DEFAULT"
  }

  verification_message_template {
    default_email_option = "CONFIRM_WITH_CODE"
    email_message        = "Your code is {####}."
    email_subject        = "Verification Code"
  }


  schema {
    attribute_data_type = "String"
    name                = "email"
  }

  schema {
    attribute_data_type = "String"
    name                = "username"
  }

  schema { //!!!!
    attribute_data_type = "String"
    name                = "profile"
  }


  password_policy {
    minimum_length  = 8
    require_numbers = true
  }
}


resource "aws_cognito_user_pool_client" "user_pool_client" {
  name                                 = "MyUserPoolClient"
  user_pool_id                         = aws_cognito_user_pool.user_pool.id
  generate_secret                      = false
  explicit_auth_flows                  = ["ADMIN_NO_SRP_AUTH"] //secure remote protocal, straight username password flow
  allowed_oauth_flows                  = ["code", "implicit"]
  # code - apps that can mantian client secret, implicit - used by web apps token is handed off directly
  allowed_oauth_flows_user_pool_client = true # enable open authentication oAuth protocol
  allowed_oauth_scopes                 = ["email", "openid"]
  #openid required for all apps which wnat to use openid connect tokens - jwt - related to jwt  # allowed permission phone, email, openid etc. Email allows to acceess emaiL-verified
  callback_urls                        = ["http://localhost:8080/callback"]
  logout_urls                          = ["http://localhost:8080/logout"]
}

output "user_pool_id" {
  value = aws_cognito_user_pool.user_pool.id
  description = "The ID of the Cognito User Pool"
}

output "user_pool_client_id" {
  value = aws_cognito_user_pool_client.user_pool_client.id
  description = "The ID of the Cognito User Pool Client"
}