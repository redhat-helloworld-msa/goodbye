GoodBuy demo
------------

This project demonstrates the motivations to use a circuit-break for REST endpoints.

It's composed by two parts

- server - REST endpoint with two resources (/api/goodbye and /api/nap). 
- client - Java client that consumes http://localhost:8080/api/nap



Server execution
================

The /api/nap resource sleeps for 30 Seconds. It's used to demonstrate the behaviour of REST producer/consumer when the resource is taking too much time to respond.


        cd server/
        mvn clean wildfly-swarm:run

Client execution
================

The client will trigger 100 Threads agains the /api/nap endpoint running on the server.

        cd client/
        vim src/main/java/com/redhat/developers/msa/goodbuy/client/Main.java
        # Uncomment the desired Thread implementation
        # Save the file and exit
        
        mvn compile exec:exec


There are 3 different Threads implementations:

- ApacheClient - It uses Apache HTTPClient to connect to the /api/nap endpoint running on server.
- TimeoutApacheClient - Apache HTTPClient is now configured to have 1 second of timeout.
- HystrixClient - It uses Feign/Hystrix. Hystrix is a circuit-break implementatio from NetFlixOSS.

When using `ApacheClient`, the server and client threads gets busy and the server stop receiving request on /api/goodbuy. This is very similar to a Denial-of-service attack.

With the `TimeoutApacheClient`, the client threads fail fast now, but the server still having all threads in the thread-pool on WAIT state. 

Using `HystrixClient`, after the 10th request, the circuit opens. The client fails fast and the server is just hit by 10 requests.
