package com.altuhin.grpc.sec03;


import com.altuhin.models.sec03.Car;
import com.altuhin.models.sec03.Dealer;
import com.altuhin.models.sec03.WeekDay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Lec06Map {
    private static final Logger log = LoggerFactory.getLogger(Lec06Map.class);

    public static void main(String[] args) {

        Car hondaCivic = Car.newBuilder()
                .setMake("Honda")
                .setModel("Civic")
                .setYear(2000)
                .setWeekDay(WeekDay.FRIDAY)
                .build();

        Car hondaAccord = Car.newBuilder()
                .setMake("Honda")
                .setModel("Accord")
                .setYear(1997)
                .setWeekDay(WeekDay.SATURDAY)
                .build();


        Dealer dealer = Dealer.newBuilder()
                .putInventory(hondaAccord.getYear(), hondaAccord)
                .putInventory(hondaCivic.getYear(), hondaCivic)
                .build();


        log.info("dealer: {}", dealer);
        log.info("2000 model: {}", dealer.getInventoryOrThrow(2000).getWeekDay());

    }
}
