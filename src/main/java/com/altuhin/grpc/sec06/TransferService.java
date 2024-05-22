package com.altuhin.grpc.sec06;

import com.altuhin.grpc.sec06.requestHandler.TransferRequestHandler;
import com.altuhin.models.sec06.TransferRequest;
import com.altuhin.models.sec06.TransferResponse;
import com.altuhin.models.sec06.TransferServiceGrpc;
import io.grpc.stub.StreamObserver;

public class TransferService extends TransferServiceGrpc.TransferServiceImplBase {
    @Override
    public StreamObserver<TransferRequest> transfer(StreamObserver<TransferResponse> responseObserver) {
        return new TransferRequestHandler(responseObserver);
    }
}
