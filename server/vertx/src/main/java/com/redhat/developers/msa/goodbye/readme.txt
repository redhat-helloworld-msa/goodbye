From this directory, use 4 event loops
vertx run --instances 4 ServerVerticle.java

OR

vertx run --instances 20 ServerVerticle2.java --cluster
vertx run --instances 180 MyWorkerVerticle.java --cluster --worker

