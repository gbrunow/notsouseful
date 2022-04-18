package com.notsouseful.resources;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("")
public class HomeResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getHome() {
        return "A Not So Useful Crypto Web Service.";
    }
}
