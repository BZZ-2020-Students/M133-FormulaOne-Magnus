package ch.formula.one.service;

import ch.formula.one.data.DataHandler;
import ch.formula.one.model.Season;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

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
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
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

    /**
     * creates a season
     *
     * @param year
     * @param winner
     */
    @Path("create")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertSeason(
            @NotEmpty
            @Size(min=4, max=4)
            @FormParam("year") String year,
            @NotEmpty
            @Size(min=1, max=40)
            @FormParam("winner") String winner
    ) {
        Season season = new Season();
        season.setSeasonUUID(UUID.randomUUID().toString());
        season.setYear(year);
        season.setWinner(winner);

        DataHandler.getInstance().insertSeason(season);

        int httpStatus = 200;
        return Response
                .status(httpStatus)
                .entity("Season erfolgreich angelegt")
                .build();
    }

    /**
     * deletes a season identified by the uuid
     *
     * @param seasonUUID
     */
    @Path("delete")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertSeason(
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String seasonUUID
    ) {
        DataHandler.getInstance().deleteSeason(seasonUUID);

        int httpStatus = 200;
        return Response
                .status(httpStatus)
                .entity("Season erfolgreich gelöscht")
                .build();
    }

    /**
     * updates a season identified by the uuid
     *
     * @param s
     */
    @Path("update")
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateSeason(
            @Valid @BeanParam Season s
    ) {
        Season season = DataHandler.getInstance().readSeasonByUUID(s.getSeasonUUID());
        season.setSeasonUUID(s.getSeasonUUID());
        season.setYear(s.getYear());
        season.setWinner(s.getWinner());

        DataHandler.getInstance().updateSeason();

        int httpStatus = 200;
        return Response
                .status(httpStatus)
                .entity("Season erfolgreich geupdated")
                .build();
    }
}