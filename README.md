
# Scalar Types

### Java Types   vs    Proto Types
        int     -->     int32/sin32
        long    -->     int64/sin64
        float   -->     float
        double  -->     double
        boolean -->     bool
        String  -->     string
        byte[]  -->     bytes

These are the basic building blocks in proto using which we can model our message. Then using that message by using composition
we can create further complex message if you want.

Serialization is super simple with the proto generated source

### Important: Proto will replace JSON. Proto represent the data which we are planning to send to another service.

One of the best feature of Proto is it doesnt support NULL.

### oneOf
one server S1 calling another server S2, that S2 server will return response, this response will be GENERIC type,
it could be SUCCESS response or it could be FAILURE response. I will be getting one of these types. that is why it is 
called oneOf.c
"oneOf" behavior can also be achieved with latest version of java using sealed class and pattern matching.

### proto message format changes
![alt text](images/pb/protoMsgFormatChanges.png)


# Unary
gRPC ia lightweight framework for developing high performance client-server application.

![alt text](images/unary/grpc_intro_ss1.png)<br>
![alt text](images/unary/ss2.png)<br>
![alt text](images/unary/ss3.png)<br>
![alt text](images/unary/communication_pattern_ss4.png)<br>
![alt text](images/unary/http1_ss5.png)<br>

[//]: # (![alt text]&#40;images/unary/http1_ss6.png&#41;<br>)

In HTTP1.1, 
1. use text based.
2. Three ways handshake.
3. we can only process ONE request in on TCP connection and every web based framework use ThreadPool to handle
lots of request, in spring its size is 200, if the request exceeded 200 then remain request will be queued and wait until 
any other thread is release/completed. while HTTP2 we can process multiple request and main benefit of HTTP2 is Binary and Multiplexing. Multiplexing mean we can send multiple request to process via one single TCP connection.

NB: There is a problem, server like Netty is not support HTTP2 that is why Google is working on HTTP3.

![alt text](images/unary/http1_ss5.png)<br>
![alt text](images/unary/unary_ss7.png)<br>

BankServiceGrpc uses void method, this is actually use reactive programming, where responseObserver keep emitting values
when we want. this is not typical Request and Response base service. it is like single request and multiple response.

```
@Override
public void getAccountBalance(BalanceCheckRequest request, StreamObserver<AccountBalance> responseObserver) {
 
 }

```
![alt text](images/unary/stream_observer_ss8.png)<br>

we have created the service class now it has to be registered with the gRPC server. For that I've created one package called
'com.altuhin.grpc.common'


```
public class BankService extends BankServiceGrpc.BankServiceImplBase {
    @Override
    public void getAccountBalance(BalanceCheckRequest request, StreamObserver<AccountBalance> responseObserver) {
        int accountNumber = request.getAccountNumber();
        AccountBalance accountBalance = AccountBalance.newBuilder()
                .setBalance(accountNumber * 10)
                .build();

        responseObserver.onNext(accountBalance);
        responseObserver.onCompleted();

    }
}





public class GrpcServer {
    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder.forPort(6565)
                .addService(new BankService())
                .build();

        server.start();
        server.awaitTermination();
    }
}


```
by default HTTP2 requires secured connection, when a client is interacts with server sung HTTP2, it assumes that server
is using the secured connection. so we have to explicitly say to postman to disable TLS.
![alt text](images/unary/disable_tls_ss9.png)<br>