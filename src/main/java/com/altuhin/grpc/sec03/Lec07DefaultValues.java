package com.altuhin.grpc.sec03;


import com.altuhin.models.sec03.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Lec07DefaultValues {
    private static final Logger log = LoggerFactory.getLogger(Lec07DefaultValues.class);

    public static void main(String[] args) {
        School school = School.newBuilder().build();

        log.info("{}", school.getId());
        log.info("{}", school.getName());
        log.info("{}", school.getAddress().getCity());

        log.info("is default?: {}", school.getAddress().equals(Address.getDefaultInstance()));

        // has
        log.info("has address: {}", school.hasAddress());

        // collection
        Library library = Library.newBuilder().build();
        log.info("collection: {}", library.getBooksList());

        // map
        Dealer dealer = Dealer.newBuilder().build();
        log.info("dealer: {}", dealer.getInventoryMap());

        // enum
        Car car = Car.newBuilder().build();
        log.info("enum: {}", car.getWeekDay());

    }
}
