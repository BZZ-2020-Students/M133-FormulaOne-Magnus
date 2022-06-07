package ch.formula.one.service;

import ch.formula.one.data.DataHandler;
import ch.formula.one.model.Season;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * services to list and read Seasons
 *
 * @author Magnus Götz
 * @version 1.0
 * @since 2022-05-23
 */
@Path("season")
public class SeasonService {
    /**
     * reads a list of season
     *
     * @return seasons as JSON
     */
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listSeasons() {
        List<Season> seasonList = DataHandler.getInstance().readAllSeasons();
        Response response = Response
                .status(200)
                .entity(seasonList)
                .build();
        return response;
    }

    /**
     * reads a season identified by the uuid
     *
     * @param seasonUUID
     * @return season
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readSeason(
            @QueryParam("uuid") String seasonUUID
    ) {
        int httpStatus = 200;
        Season season = DataHandler.getInstance().readSeasonByUUID(seasonUUID);
        if (season == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(season)
                .build();
    }
}
