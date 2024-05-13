package com.altuhin.grpc.sec05.parser;

import com.altuhin.models.sec05.v1.Television;
import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// this is the Client class
public class V1Parser {
    private static final Logger log = LoggerFactory.getLogger(V1Parser.class);
    public static void parse(byte[] bytes) throws InvalidProtocolBufferException {

        Television television = Television.parseFrom(bytes);
        log.info("brand: {}", television.getBrand());
        log.info("year: {}", television.getYear());

    }
}
