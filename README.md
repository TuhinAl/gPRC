# gPRC
#Scalar Types

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
