package com.redhat.developers.msa.goodbye;

import io.vertx.core.AbstractVerticle;
import java.util.Date;

public class MyWorkerVerticle extends AbstractVerticle {
    public void start() throws Exception {
        vertx.eventBus().consumer("mychannel", message -> {            
            System.out.println("[Worker] Received request on Thread: " + Thread.currentThread().getName());
            // String body = (String) message.body();
            Pi.computePi(20000);
            message.reply("Nap from " + new Date().toString());
        });
    }
}