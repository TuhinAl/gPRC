package com.altuhin.grpc.sec11;

import com.altuhin.grpc.sec11.repository.AccountRepository;
import com.altuhin.models.sec11.*;
import com.google.common.util.concurrent.Uninterruptibles;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static com.altuhin.grpc.sec11.repository.AccountRepository.getBalance;

public class DeadlineBankService extends BankServiceGrpc.BankServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(DeadlineBankService.class);

    /**
     * Unary
     */
    @Override
    public void getAccountBalance(BalanceCheckRequest request, StreamObserver<AccountBalance> responseObserver) {
        int accountNumber = request.getAccountNumber();
        Integer balance = getBalance(accountNumber);
        AccountBalance accountBalance = AccountBalance.newBuilder()
                .setAccountNumber(accountNumber)
                .setBalance(balance * 10)
                .build();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        responseObserver.onNext(accountBalance);
        responseObserver.onCompleted();

    }


    /**
     * Ideally we should do some input validation. But we are going to assume only happy path scenarios.
     * Because, in gRPC, there are multiple ways to communicate the error message to the client.
     * It has to be discussed in detail separately.
     * Assumption: account # 1 - 10 & withdraw amount is multiple of $10
     */
    @Override
    public void withdraw(WithdrawRequest request, StreamObserver<Money> responseObserver) {

        int accountNumber = request.getAccountNumber();
        int requestAmount = request.getAmount();
        Integer balance = getBalance(accountNumber);

        if (requestAmount > balance) {
            responseObserver.onError(Status.FAILED_PRECONDITION.asRuntimeException());
            return;
        }

        for (int i = 0; i < (requestAmount / 10); i++) {
            Money money = Money.newBuilder().setAmount(10).build();
            responseObserver.onNext(money);
            log.info("money sent {}", money);
            AccountRepository.deductAmount(accountNumber, 10);
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        }
        responseObserver.onCompleted();
    }

}
