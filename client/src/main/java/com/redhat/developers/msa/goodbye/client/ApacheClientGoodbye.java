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

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class ApacheClientGoodbye extends Thread {

    public void run() {
        // Apache HTTPClient creation
        HttpGet httpGet = new HttpGet("http://localhost:8080/api/goodbye");
        HttpClient httpClient = HttpClientBuilder.create().build();

        // Service invocation
        String result = null;
        try {
            result = EntityUtils.toString(httpClient.execute(httpGet).getEntity());
        } catch (Exception e) {
            // Fallback
            result = "Nap message (Fallback)";
        }
        System.out.println(String.format("#%s - %s", this.getName(), result));
    }

}
