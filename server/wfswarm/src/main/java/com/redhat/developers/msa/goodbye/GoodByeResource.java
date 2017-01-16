/**
 * JBoss, Home of Professional Open Source
 * Copyright 2016, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 * <p/>
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
package com.redhat.developers.msa.goodbye;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/")
public class GoodByeResource {

    @GET
    @Path("/goodbye")
    @Produces("text/plain")
    public String goodbye() {
        String msg = "Goodbye on " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS").format(new Date()); 
        System.out.println(msg);
        return msg;
    }

    @GET
    @Path("/nap")
    @Produces("text/plain")
    public String goodbyeNap() throws InterruptedException {
        System.out.println("Received request to nap 30 seconds");
        // Sleep 30 seconds
        Thread.sleep(30000);
        System.out.println("Back from the nap");
        return "Nap from " + new Date().toString();
    }
}
