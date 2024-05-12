package com.altuhin.grpc.sec02;

import com.altuhin.models.sec02.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProtoDemo {
    private static final Logger log = LoggerFactory.getLogger(ProtoDemo.class);

    public static void main(String[] args) {

        // create person1
        Person person1 = createPerson();
        // create another instance with same values
        Person person2 = createPerson();

        // compare
        log.info("equals {}", person1.equals(person2));
        log.info("== {}", person1 == person2);
        // create another instance with diff values
        Person person3 = person1
                .toBuilder()
                .setName("Tanvir Hasnan")
                .build();
        log.info("person3: {}", person3);
        // compare
        // null
        Person person4 = person1
                .toBuilder()
                .clearName()
                .build();
        log.info("person4 {}", person4);
    }

    private static Person createPerson() {
        return   Person.newBuilder()
                .setName("Alauddin Tuhin")
                .setAge(33)
                .build();

    }
}
