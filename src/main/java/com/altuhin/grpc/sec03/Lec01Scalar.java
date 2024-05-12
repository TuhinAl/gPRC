package com.altuhin.grpc.sec03;

import com.altuhin.grpc.seco2.ProtoDemo;
import com.altuhin.models.sec03.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec01Scalar {
    private static final Logger log = LoggerFactory.getLogger(Lec01Scalar.class);

    public static void main(String[] args) {
        Person person = Person.newBuilder()
                .setLastName("Tuhin")
                .setAge(27)
                .setEmail("tuhin@gmail.com")
                .setSalary(25000.50)
                .setEmployed(true)
                .setBalance(-12345674L)
                .build();
        log.info("{}", person);
    }
}
