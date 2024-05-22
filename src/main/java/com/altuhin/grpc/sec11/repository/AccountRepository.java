package com.altuhin.grpc.sec11.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AccountRepository {

    private static final Logger log = LoggerFactory.getLogger(AccountRepository.class);

    private static final Map<Integer, Integer> database = IntStream.rangeClosed(1, 10)
            .boxed()
            .collect(Collectors.toConcurrentMap(
                    Function.identity(), v -> 100
            ));

    public static Integer getBalance(int accountNumber) {
        return database.get(accountNumber);
    }

    public static void deductAmount(int accountNumber, int amount) {
      /*  Integer balance = database.get(accountNumber);
        log.info("previous balance {}", balance);
        balance -= amount;
        database.put(accountNumber, balance);
        log.info("after balance {}", balance);*/

         database.computeIfPresent(accountNumber, (k, v) -> v - amount);
    }
    public static void addAmount(int accountNumber, int amount) {
         database.computeIfPresent(accountNumber, (k, v) -> v + amount);
    }
}
