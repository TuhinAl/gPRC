package com.altuhin.grpc.sec03;

import com.altuhin.models.sec03.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec03PerformanceTest {
    private static final Logger log = LoggerFactory.getLogger(Lec03PerformanceTest.class);
private static final ObjectMapper mapper = new ObjectMapper();
    public static void main(String[] args) {
        Person protoPerson = Person.newBuilder()
                .setLastName("Tuhin")
                .setAge(27)
                .setEmail("tuhin@gmail.com")
                .setSalary(25000.50)
                .setEmployed(true)
                .setBankAccountNumber(1234567890123L)
                .setBalance(-12345674)
                .build();

        JsonPerson jsonPerson = new JsonPerson("Tuhin", 27, "tuhin@gmail.com",
                true, 25000.50, 1234567890123L, -12345674);
//        log.info("{}", person);

        for (int i = 0; i < 5; i++) {
            runTest("JSON", () -> json(jsonPerson));
            runTest("PROTO", () -> proto(protoPerson));
        }
    }
    private static void proto(Person person) {
        try {
            byte[] bytes = person.toByteArray();
            Person.parseFrom(bytes);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }
    private static void json(JsonPerson jsonPerson) {
        try {
          var  bytes = mapper.writeValueAsBytes(jsonPerson);
            mapper.readValue(bytes, JsonPerson.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static void runTest(String testName, Runnable runnable) {
        var start = System.currentTimeMillis();
        for (int i = 0; i < 5_000_000; i++) {
            runnable.run();
        }
        var end = System.currentTimeMillis();
        log.info("time taken fro {}  - {} ms", testName, (end-start));
    }
}
