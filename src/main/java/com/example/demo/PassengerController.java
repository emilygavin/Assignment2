package com.example.demo;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/passengers")
@AllArgsConstructor
public class PassengerController {
    private final PassengerService passengerService;

    @GetMapping
    public List<Passenger> fetchAllPassengers(){
        return passengerService.getAllPassengers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Passenger registerNewPassenger (@RequestBody Passenger passenger) {
        return passengerService.addNewPassenger(passenger);
    }
}
