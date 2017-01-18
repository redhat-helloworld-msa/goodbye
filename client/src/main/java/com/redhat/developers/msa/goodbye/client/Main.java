/**
 * JBoss, Home of Professional Open Source
 * Copyright 2016, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redhat.developers.msa.goodbye.client;

public class Main {

    private static final int NUMBER_THREADS = 200;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting Threads");
        for (int x = 0; x < NUMBER_THREADS; x++) {
            Thread.sleep(20);
            // new ApacheClientGoodbye().start();
             new ApacheClient().start();
            // new TimeoutApacheClient().start();
            // new HystrixClient().start();
        }
        System.out.println(NUMBER_THREADS + " Threads running...");
    }

}
