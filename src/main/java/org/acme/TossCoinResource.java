package org.acme;

import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Path("/toss")
public class TossCoinResource {

    private static Counter heads;

    public TossCoinResource(MeterRegistry registry) {
        heads = Counter.builder("coin_heads")
        .description("Total number of heads recorded when tossing the coin")
        .register(registry);
    }

    @GET
    public Response tossACoin() {
        Random rand = new Random();
        int result = rand.nextInt(2); // generates a random number between 0 and 1
        if (result == 0) {
            heads.increment();
        }
        return Response.ok().build();
    }
}