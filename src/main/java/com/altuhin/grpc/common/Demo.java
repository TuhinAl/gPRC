package com.altuhin.grpc.common;

import com.altuhin.grpc.sec06.BankService;
import com.altuhin.grpc.sec06.TransferService;

/**
 *  a simple class to start the server with specific services for demo purpose.
 */

// This is the gRPC Server
public class Demo {
    public static void main(String[] args) {
        GrpcServer.create(new BankService(), new TransferService())
                .start()
                .await();
    }
}
