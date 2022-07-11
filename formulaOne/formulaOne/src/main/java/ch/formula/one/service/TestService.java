package ch.formula.one.service;

import ch.formula.one.CreateJSON;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * test service
 */

@Path("/test")
public class TestService {

    @PermitAll
    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response test() {
        return Response
                .status(200)
                .entity("Test erfolgreich")
                .build();
    }

    @PermitAll
    @GET
    @Path("resetJSON")
    @Produces(MediaType.TEXT_PLAIN)
    public Response resetJSON() {
        CreateJSON.reset();
        return Response
                .status(200)
                .entity("Test erfolgreich")
                .build();
    }
}
