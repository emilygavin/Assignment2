package com.example.demo;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Passenger {
    @NonNull
    private String title;
    @NonNull
    private String name;
    @Id
    private String ID;
    @NonNull
    private String phoneNumber;
    @NonNull
    private Integer age;

    public Passenger(String title, String name, String phoneNumber, Integer age) {
        this.title = title;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public Passenger() {
    }
}
