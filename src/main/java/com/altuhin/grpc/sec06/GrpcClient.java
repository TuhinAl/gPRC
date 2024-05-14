package com.altuhin.grpc.sec06;

import com.altuhin.models.sec06.AccountBalance;
import com.altuhin.models.sec06.BalanceCheckRequest;
import com.altuhin.models.sec06.BankServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class is going to act like a Client
 */
public class GrpcClient {
    private static final Logger log = LoggerFactory.getLogger(GrpcClient.class);

    public static void main(String[] args) {
    // create channel
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();

        // create the Stub
        var stub = BankServiceGrpc.newBlockingStub(channel);
        var balance = stub.getAccountBalance(BalanceCheckRequest.newBuilder().setAccountNumber(2).build());

        //print the balance
        log.info("balance: {}", balance);
    }
}
