package com.pwr.pizzaplatformbackend.repository;

import com.pwr.pizzaplatformbackend.model.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface BucketRepository extends JpaRepository<Bucket, Long> {

    @Query(value = "SELECT * FROM bucket JOIN pizza ON bucket.id = pizza.bucket_id", nativeQuery = true)
    List<Bucket> findAll();

    @Query(value = "SELECT * FROM bucket JOIN pizza ON bucket.id = pizza.bucket_id WHERE email=:email ", nativeQuery = true)
    Bucket findByUserEmail(@Param("email") String email);
}