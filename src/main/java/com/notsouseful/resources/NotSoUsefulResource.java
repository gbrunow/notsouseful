package com.notsouseful.resources;

import com.notsouseful.core.EncryptedStats;
import com.notsouseful.core.NSUCipher;
import com.notsouseful.core.Stats;
import java.math.BigDecimal;
import javax.validation.constraints.NotEmpty;
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
    @Produces(MediaType.TEXT_PLAIN)
    public String getNSU() {
        return "A Not So Useful Resource.";
    }

    @GET
    @Path("stats")
    @Produces(MediaType.APPLICATION_JSON)
    public Stats GetStats() {
        return stats;
    }

    @POST
    @Path("push-and-recalculate")
    @Produces(MediaType.APPLICATION_JSON)
    public Stats PushAndRecalculate(@NotEmpty(message = "is missing a required parameter") String number) {
        try {
            BigDecimal decimal = new BigDecimal(number);
            stats.addSample(decimal);

            return stats;
        } catch (NumberFormatException ex) {
            throw new WebApplicationException("invalid number", Status.BAD_REQUEST);
        }
    }

    @POST
    @Path("push-recalculate-and-encrypt")
    @Produces(MediaType.APPLICATION_JSON)
    public EncryptedStats PushRecalculateAndEncrypt(
            @NotEmpty(message = "is missing a required parameter") String number) {
        return new EncryptedStats(PushAndRecalculate(number));
    }

    @POST
    @Path("decrypt")
    @Produces(MediaType.TEXT_PLAIN)
    public String Decrypt(@NotEmpty(message = "is missing a required parameter") String encrypted) {
        return NSUCipher.getInstance().decrypt(encrypted);
    }

}
