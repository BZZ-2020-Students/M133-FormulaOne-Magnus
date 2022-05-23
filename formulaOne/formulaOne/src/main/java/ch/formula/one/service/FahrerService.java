package ch.formula.one.service;

import ch.formula.one.data.DataHandler;
import ch.formula.one.model.Fahrer;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * services to list and read Fahrers
 *
 * @author Magnus Götz
 * @version 1.0
 * @since 2022-05-23
 */
@Path("fahrer")
public class FahrerService {
    /**
     * reads a list of fahrer
     *
     * @return fahrer
     */
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listFahrers() {
        List<Fahrer> fahrerList = DataHandler.getInstance().readAllFahrers();
        Response response = Response
                .status(200)
                .entity(fahrerList)
                .build();
        return response;
    }

    /**
     * reads a fahrer identified by the uuid
     *
     * @param fahrerUUID
     * @return fahrer
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readFahrer(
            @QueryParam("uuid") String fahrerUUID
    ) {
        int httpStatus = 200;
        Fahrer fahrer = DataHandler.getInstance().readFahrerByUUID(fahrerUUID);
        if (fahrer == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(fahrer)
                .build();
    }
}
