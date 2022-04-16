package com.notsouseful.resources;

import javax.ws.rs.Path;
import javax.ws.rs.GET;

@Path("nsu")
public class NotSoUsefulResource {
    
    @GET
    public String get() {
        return "A Not So Useful Crypto Web Service";
    }
}
