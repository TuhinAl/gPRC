package com.altuhin.grpc.sec04;


import com.altuhin.models.common.Address;
import com.altuhin.models.sec03.Credentials;
import com.altuhin.models.sec03.Email;
import com.altuhin.models.sec03.Phone;
import com.altuhin.models.sec04.Person;
import com.altuhin.models.sec04.common.Car;
import com.altuhin.models.sec04.common.WeekDay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Lec01ImportModules {
    private static final Logger log = LoggerFactory.getLogger(Lec01ImportModules.class);

    public static void main(String[] args) {

        Address address = Address.newBuilder().setCity("Jashore").build();
        Car car = Car.newBuilder().setWeekDay(WeekDay.MONDAY).build();
        Person person = Person.newBuilder()
                .setLastName("Tuhin")
                .setAge(27)
                .setAddress(address)
                .setCar(car)
                .build();

        log.info("person: {}", person);

    }

}
