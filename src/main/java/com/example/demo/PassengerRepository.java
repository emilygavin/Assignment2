package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PassengerRepository extends MongoRepository<Passenger, String> {

    @Query("SELECT p FROM Passenger p WHERE p.age1 > ?1 and p.age2 <?2")
    List<Passenger> findByAgeRange(int age1, int age2);
}
