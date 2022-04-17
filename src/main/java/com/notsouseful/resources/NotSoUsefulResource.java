package com.notsouseful.resources;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.notsouseful.core.Stats;

import io.dropwizard.validation.Validated;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

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
    public Stats PushAndRecalculate(String number) {
        SafeBigDecimal safe = new SafeBigDecimal(number);

        if (safe.getBigDecimal() == null) {
            throw new WebApplicationException("invalid number", Status.BAD_REQUEST);
        }

        this.stats.addSample(safe.getBigDecimal());

        return this.stats;
    }
}

class SafeBigDecimal {
    private final BigDecimal decimal;

    public SafeBigDecimal(String number) {
        BigDecimal temp = null;
        try {
            temp = new BigDecimal(number);
        } catch (NumberFormatException ex) {
            temp = null;
        } finally {
            this.decimal = temp;
        }
    }

    public BigDecimal getBigDecimal() {
        return this.decimal;
    }
}
