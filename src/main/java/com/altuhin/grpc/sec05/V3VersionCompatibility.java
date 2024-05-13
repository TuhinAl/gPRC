package com.altuhin.grpc.sec05;


import com.altuhin.grpc.sec05.parser.V1Parser;
import com.altuhin.grpc.sec05.parser.V2Parser;

import com.altuhin.grpc.sec05.parser.V3Parser;
import com.altuhin.models.sec05.v3.Television;
import com.altuhin.models.sec05.v3.Type;
import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class V3VersionCompatibility {

    private static final Logger log = LoggerFactory.getLogger(V3VersionCompatibility.class);
    public static void main(String[] args) throws InvalidProtocolBufferException {
        Television television = Television.newBuilder()
                .setBrand("Samsung")
                .setType(Type.LED)
                .build();

        V1Parser.parse(television.toByteArray());
        V2Parser.parse(television.toByteArray());
        V3Parser.parse(television.toByteArray());

    }
}
