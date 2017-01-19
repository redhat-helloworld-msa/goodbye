From this directory, use 4 event loops
vertx run --instances 4 -Dvertx.options.workerPoolSize=100 ServerVerticle.java

OR

vertx run --instances 20 -Dvertx.options.workerPoolSize=100 ServerVerticle2.java --cluster
vertx run --instances 180 -Dvertx.options.workerPoolSize=100 MyWorkerVerticle.java --cluster --worker

