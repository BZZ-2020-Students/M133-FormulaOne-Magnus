package ch.formula.one.service;

import ch.formula.one.data.DataHandler;
import ch.formula.one.model.Saison;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * services to list and read Saisons
 */
@Path("saison")
public class SaisonService {
    /**
     * reads a list of saison
     * @return saisons as JSON
     */
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listSaisons() {
        List<Saison> saisonList = DataHandler.getInstance().readAllSaisons();
        Response response = Response
                .status(200)
                .entity(saisonList)
                .build();
        return response;
    }
    /**
     * reads a saison identified by the uuid
     * @param saisonUUID
     * @return saison
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readSaison(
            @QueryParam("uuid") String saisonUUID
    ) {
        int httpStatus = 200;
        Saison saison = DataHandler.getInstance().readSaisonByUUID(saisonUUID);
        if (saison == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(saison)
                .build();
    }
}
