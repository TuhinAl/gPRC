package com.altuhin.grpc.sec05;

import com.altuhin.grpc.sec05.parser.V1Parser;
import com.altuhin.grpc.sec05.parser.V2Parser;
import com.altuhin.grpc.sec05.parser.V3Parser;
import com.altuhin.models.sec05.v1.Television;
import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class V1VersionCompatibility {

    private static final Logger log = LoggerFactory.getLogger(V1VersionCompatibility.class);
    public static void main(String[] args) throws InvalidProtocolBufferException {
        Television television = Television.newBuilder()
                .setBrand("Samsung")
                .setYear("2010")
                .build();

        V1Parser.parse(television.toByteArray());
        V2Parser.parse(television.toByteArray());
        V3Parser.parse(television.toByteArray());

    }
}
