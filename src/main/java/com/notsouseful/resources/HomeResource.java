package com.notsouseful.resources;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.common.net.MediaType;

import javax.ws.rs.GET;

@Path("")
public class HomeResource {
    
    @GET
    public String getHome() {
        return "A Not So Useful Crypto Web Service.";
    }
}
