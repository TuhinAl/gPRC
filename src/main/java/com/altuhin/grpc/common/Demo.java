package com.altuhin.grpc.common;

import com.altuhin.grpc.sec06.BankService;

/**
 *  a simple class to start the server with specific services for demo purpose.
 */
public class Demo {
    public static void main(String[] args) {
        GrpcServer.create(new BankService())
                .start()
                .await();
    }
}
