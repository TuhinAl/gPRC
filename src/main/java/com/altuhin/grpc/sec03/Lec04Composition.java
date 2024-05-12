package com.altuhin.grpc.sec03;

import com.altuhin.models.sec03.Address;
import com.altuhin.models.sec03.Person;
import com.altuhin.models.sec03.School;
import com.altuhin.models.sec03.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec04Composition {
    private static final Logger log = LoggerFactory.getLogger(Lec04Composition.class);

    public static void main(String[] args) {
        //create address
        Address address = Address.newBuilder()
                .setStreet("Kuril Chowrasta")
                .setCity("Dhaka")
                .setState("Dhaka")
                .build();
        //create student
        Student student = Student.newBuilder()
                .setName("Tuhin")
                .setAddress(address)
                .build();
        //create school
        School school = School.newBuilder()
                .setId(1)
                .setName("Eastern University")
                .setAddress(address.toBuilder().setStreet("Dhanmondi 4/A").build())
                .build();

        log.info("school: {}", school);
        log.info("student: {}", student);

    }
}
