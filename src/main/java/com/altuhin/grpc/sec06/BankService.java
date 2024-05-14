package com.altuhin.grpc.sec06;

import com.altuhin.grpc.sec06.repository.AccountRepository;
import com.altuhin.models.sec06.AccountBalance;
import com.altuhin.models.sec06.BalanceCheckRequest;
import com.altuhin.models.sec06.BankServiceGrpc;
import io.grpc.stub.StreamObserver;

import static com.altuhin.grpc.sec06.repository.AccountRepository.getBalance;

public class BankService extends BankServiceGrpc.BankServiceImplBase {
    @Override
    public void getAccountBalance(BalanceCheckRequest request, StreamObserver<AccountBalance> responseObserver) {
        int accountNumber = request.getAccountNumber();
        Integer balance = getBalance(accountNumber);
        AccountBalance accountBalance = AccountBalance.newBuilder()
                .setAccountNumber(accountNumber)
                .setBalance(balance * 10)
                .build();

        responseObserver.onNext(accountBalance);
        responseObserver.onCompleted();

    }
}
