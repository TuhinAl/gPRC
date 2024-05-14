package com.altuhin.grpc.common;

import com.altuhin.grpc.sec06.BankService;
import io.grpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * we will talk about gRPC server feature a lot
 * like
 *  a) all the communication pattern
 *  b) how to do error handling
 *  c) intercepting the request
 *  d) sending/receiving the header
 *
 */
public class GrpcServer {
    private static final Logger log = LoggerFactory.getLogger(GrpcServer.class);
/*    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder.forPort(6565)
                .addService(new BankService())
                .build();

        server.start();
        server.awaitTermination();
    }*/

    private final Server server;

    public GrpcServer(Server server) {
        this.server = server;
    }

    public static GrpcServer create(BindableService... serviceList) {
        return create(6565, serviceList);
    }
    public static GrpcServer create(int port, BindableService... serviceList) {
        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(port);
        Arrays.asList(serviceList).forEach( serverBuilder::addService);
        return new GrpcServer(serverBuilder.build());
    }
    public  GrpcServer start() {
        List<String> serviceList = server.getServices()
                .stream()
                .map(ServerServiceDefinition::getServiceDescriptor)
                .map(ServiceDescriptor::getName)
                .toList();

        try {
            server.start();
            log.info("server started ... listening on port: {} services: {}", server.getPort(), serviceList);
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void await() {
        try {
            server.awaitTermination();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void stop() {
        server.shutdown();
        log.info("server stopped.");
    }
}
