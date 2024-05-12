
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
called oneOf.
"oneOf" behavior can also be achieved with latest version of java using sealed class and pattern matching.
