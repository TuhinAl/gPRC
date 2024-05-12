package com.altuhin.grpc.sec03;


import com.altuhin.models.sec03.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Lec08OneOf {
    private static final Logger log = LoggerFactory.getLogger(Lec08OneOf.class);

    public static void main(String[] args) {

        Email email = Email.newBuilder()
                .setAddress("tuhin@gmail.com")
                .setPassword("tuhin")
                .build();

        Phone phone = Phone.newBuilder()
                .setNumber(17269677)
                .setCode(880)
                .build();
    /*    login(Credentials.newBuilder().setEmail(email).build());
        login(Credentials.newBuilder().setPhone(phone).build());*/
        login(Credentials.newBuilder().setPhone(phone).setEmail(email).build());

//        log.info("enum: {}", );

    }

    private static void login(Credentials credentials) {
        switch (credentials.getLoginTypeCase()) {
            case EMAIL -> log.info("email: -> {}", credentials.getEmail());
            case PHONE -> log.info("phone: -> {}", credentials.getPhone());
        }
    }

}
