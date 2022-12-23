package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;


@SpringBootTest
class PassengerTest {

	@Autowired
	PassengerController passengerController;

	@Test
	public void testFindAllPassengers (){
		List<Passenger> passengers = passengerController.fetchAllPassengers();

		assertThat(passengers).isEqualTo(passengerController.fetchAllPassengers());
	}

//	@Test
//	public void testFindFirstPassenger (){
//		Passenger passenger = new Passenger();
//		passenger.setAge(21);
//		passenger.setName("Emily Gavin");
//		passenger.setTitle("Miss");
//		passenger.setPhoneNumber("0838674899");
//		passenger.setID("6364f32aec320c72657d7c6c");
//
//		assertThat(passengerController.fetchAllPassengers().get(0)).isEqualTo(passenger);
//	}



	public String generateString() {
			String uuid = UUID.randomUUID().toString();
			uuid.replace("-", "");
			return  uuid;
		}

	@Test
	public void testCreateNewPassenger(){
		Passenger passenger = new Passenger();
		passenger.setAge(45);
		passenger.setName("Michael Jackson");
		passenger.setTitle("Mr");
		passenger.setPhoneNumber("087468648171");
		Long.toHexString(Double.doubleToLongBits(Math.random()));
		UUID.randomUUID().toString();
		String randomId = generateString();
		passenger.setID(randomId);

		assertThat(passengerController.registerNewPassenger(passenger)).isEqualTo(passenger);
	}

	@Test
	public void testCreateNewPassengerWithDuplicateIDError(){
		Passenger passenger = new Passenger();
		passenger.setAge(21);
		passenger.setName("Emily Gavin");
		passenger.setTitle("Miss");
		passenger.setPhoneNumber("0838674899");
		passenger.setID("6364f32aec320c72657d7c6c");

		Exception exception = assertThrows(IllegalStateException.class, () -> {
			passengerController.registerNewPassenger(passenger);
		});

		String expectedMessage = "id already exists";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void testCreateNewPassengerWithInvalidAge(){
		Passenger passenger = new Passenger();
		passenger.setAge(15);
		passenger.setName("Emily Gavin");
		passenger.setTitle("Miss");
		passenger.setPhoneNumber("08306592312");
		passenger.setID("8erguhd823903oketoi4");

		Exception exception = assertThrows(IllegalStateException.class, () -> {
			passengerController.registerNewPassenger(passenger);
		});

		String expectedMessage = "Passenger cannot be under the age of 16";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void testCreateNewPassengerWithInvalidName(){
		Passenger passenger = new Passenger();
		passenger.setAge(25);
		passenger.setName("Ma");
		passenger.setTitle("Mr");
		passenger.setPhoneNumber("0894537412490");
		passenger.setID("vwiurhb234gy239ijef");

		Exception exception = assertThrows(IllegalStateException.class, () -> {
			passengerController.registerNewPassenger(passenger);
		});

		String expectedMessage = "Passenger name too short (must be greater than 3 characters)";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void testCreateNewPassengerWithInvalidPhoneNumber(){
		Passenger passenger = new Passenger();
		passenger.setAge(25);
		passenger.setName("Macklemore");
		passenger.setTitle("Mr");
		passenger.setPhoneNumber("123");
		passenger.setID("7eyuhwevi2394ig34hf");

		Exception exception = assertThrows(IllegalStateException.class, () -> {
			passengerController.registerNewPassenger(passenger);
		});

		String expectedMessage = "Passenger phone number too short (must be greater than 7 characters)";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void testCreateNewPassengerWithInvalidTitle(){
		Passenger passenger = new Passenger();
		passenger.setAge(25);
		passenger.setName("Macklemore");
		passenger.setTitle("Mister");
		passenger.setPhoneNumber("0838947592833");
		passenger.setID("3049u8g3uhvim28h374g");

		Exception exception = assertThrows(IllegalStateException.class, () -> {
			passengerController.registerNewPassenger(passenger);
		});

		String expectedMessage = "Not a valid title (must be Miss, Mr or Mrs)";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
}
