
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