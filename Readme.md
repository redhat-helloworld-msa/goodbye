GoodBye demo
------------

This project demonstrates the motivations to use a circuit-breaker for REST endpoints as well as Vert.x's capability to offload "blocking" tasks to background threads.
Keeping the foreground event-loop always responsive.

It's composed by two parts

- server - REST endpoint with two resources (/api/goodbye and /api/nap). There are three different implementations for the Server (WildFly Swarm, Spring Boot, Vert.x).
- client - Java client that consumes http://localhost:8080/api/nap



Server execution
================

The /api/nap resource blocks for several seconds as it is calculating Pi. It's used to demonstrate the behaviour of REST producer/consumer when the resource is taking too much time to respond.


        $ cd server/<implementation>
        
        # For WildFly Swarm
        $ mvn clean compile wildfly-swarm:run
        
        # For Spring Boot
        $ mvn clean compile spring-boot:run

        # For Vert.x
        $ vertx run --instances 1 -Dvertx.options.workerPoolSize=100 src/main/java/com/redhat/developers/msa/goodbye/ServerVerticle.java
        
        Note: Vert.x also runs as a fat jar, in this case we just wanted to "fix" the thread pool to 100 like it was for Spring Boot

The server will accept requests on <http://localhost:8080/api/goodbye> and <http://localhost:8080/api/nap>.

Client execution
================

The client will trigger 210 Threads against the /api/nap endpoint running on the server.

        cd client/
        vim src/main/java/com/redhat/developers/msa/goodbye/client/Main.java
        # Uncomment the desired Thread implementation
        # Save the file and exit
        
        mvn compile exec:java


There are 3 different Threads implementations:

- ApacheClient - It uses Apache HTTPClient to connect to the /api/nap endpoint running on server.
- TimeoutApacheClient - Apache HTTPClient is now configured to have 1 second of timeout.
- HystrixClient - It uses Feign/Hystrix. Hystrix is a circuit-break implementation from NetflixOSS.

When using `ApacheClient`, the server and client threads go busy and the server stops responding on /api/goodbye. This is very similar to a denial-of-service attack.  

With the `TimeoutApacheClient`, the client threads fail fast now, but the server still having all threads in the thread-pool on WAIT state. 

Using `HystrixClient`, after the 10th request, the circuit opens. The client fails fast and the server is just hit by 10 requests.
