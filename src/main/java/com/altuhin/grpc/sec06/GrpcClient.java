package com.altuhin.grpc.sec06;

import com.altuhin.models.sec06.AccountBalance;
import com.altuhin.models.sec06.BalanceCheckRequest;
import com.altuhin.models.sec06.BankServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;


/**
 * This class is going to act like a Client
 */
public class GrpcClient {
    private static final Logger log = LoggerFactory.getLogger(GrpcClient.class);

    public static void main(String[] args) throws InterruptedException {
        // create channel
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();

        // create the Stub (Blocking)
        // var stub = BankServiceGrpc.newBlockingStub(channel);

        var stub = BankServiceGrpc.newStub(channel);
        // we are not going to block the thread,
        // var balance = stub.getAccountBalance(BalanceCheckRequest.newBuilder().setAccountNumber(2).build());

        //we are going to do things asynchronously ao there are no return value

        /**
         * this program is exited, right? (before use Thread.sleep()) It is mainly because the main thread invoked the method,
         * but it does not wait for thr response to come. this is asynchronous!!!!!!!!! and it is exit immediately. because of that
         *  could not print anything.
         */
        stub.getAccountBalance(BalanceCheckRequest.newBuilder().setAccountNumber(2).build(), new StreamObserver<AccountBalance>() {
            @Override
            public void onNext(AccountBalance accountBalance) {
                log.info("{}", accountBalance);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                log.info("completed.");
            }
        });

        log.info("Done");
        Thread.sleep(Duration.ofSeconds(10));

    }
}
