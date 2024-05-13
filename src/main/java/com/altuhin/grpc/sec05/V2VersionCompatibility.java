package com.altuhin.grpc.sec05;


import com.altuhin.grpc.sec05.parser.V1Parser;
import com.altuhin.grpc.sec05.parser.V2Parser;
import com.altuhin.models.sec05.v2.Television;
import com.altuhin.models.sec05.v2.Type;
import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class V2VersionCompatibility {

    private static final Logger log = LoggerFactory.getLogger(V2VersionCompatibility.class);
    public static void main(String[] args) throws InvalidProtocolBufferException {
        Television television = Television.newBuilder()
                .setBrand("Samsung")
                .setModel("2010")
                .setType(Type.LED)
                .build();

        V1Parser.parse(television.toByteArray());
        V2Parser.parse(television.toByteArray());

    }
}
