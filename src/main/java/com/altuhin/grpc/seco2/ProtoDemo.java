package com.altuhin.grpc.seco2;

import com.altuhin.models.sec01.PersonOuterClass;
import com.altuhin.models.sec02.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProtoDemo {
    private static final Logger log = LoggerFactory.getLogger(ProtoDemo.class);

    public static void main(String[] args) {

        Person person = Person.newBuilder()
                .setName("Tanvir Hasnan")
                .setAge(33)
                .build();
        log.info("{}", person);
    }
}
