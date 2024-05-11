package com.altuhin.grpc.sec01;

import com.altuhin.models.sec01.PersonOuterClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleProtoDemo {
    private static final Logger log = LoggerFactory.getLogger(SimpleProtoDemo.class);

    public static void main(String[] args) {
        // create Person instance
        PersonOuterClass.Person person = PersonOuterClass.Person
                .newBuilder()
                .setName("Alauddin Tuhin")
                .setAge(28)
                .build();
        log.info("{}", person);

    }
}
