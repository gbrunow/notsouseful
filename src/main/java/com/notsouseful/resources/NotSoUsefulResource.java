package com.notsouseful.resources;

import com.notsouseful.core.SafeBigDecimal;
import com.notsouseful.core.Stats;
import javax.validation.constraints.NotEmpty;
import javax.validation.Valid;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

@Path("nsu")
public class NotSoUsefulResource {
    private Stats stats = new Stats();

    @GET
    public String getNSU() {
        return "A Not So Useful Resource.";
    }

    @POST
    @Path("push-and-recalculate")
    @Produces(MediaType.APPLICATION_JSON)
    public Stats PushAndRecalculate(@Valid @NotEmpty(message = "is missing a required parameter") String number) {
        SafeBigDecimal safe = new SafeBigDecimal(number);

        if (safe.getBigDecimal() == null) {
            throw new WebApplicationException("invalid number", Status.BAD_REQUEST);
        }

        this.stats.addSample(safe.getBigDecimal());

        return this.stats;
    }
}
