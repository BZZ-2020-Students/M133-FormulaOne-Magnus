package ch.formula.one.service;

import ch.formula.one.data.DataHandler;
import ch.formula.one.model.Driver;
import ch.formula.one.model.Season;
import ch.formula.one.model.Team;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

/**
 * services to list and read Teams
 *
 * @author Magnus Götz
 * @version 1.0
 * @since 2022-05-23
 */
@Path("team")
public class TeamService {
    /**
     * reads a list of team
     *
     * @return fahrer
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listTeams() {
        List<Team> teamList = DataHandler.readAllTeams();
        return Response
                .status(200)
                .entity(teamList)
                .build();
    }

    /**
     * reads a team identified by the uuid
     *
     * @param teamUUID
     * @return team
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readTeam(
            @QueryParam("uuid") String teamUUID
    ) {
        int httpStatus = 200;
        Team team = DataHandler.readTeamByUUID(teamUUID);
        if (team == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(team)
                .build();
    }

    /**
     * creates a team
     *
     * @param name
     * @param teamPrincipal
     * @param engine
     * @param chassis
     * @param seasonUUID
     */
    @Path("create")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertTeam(
            @NotEmpty
            @Size(min = 1, max = 40)
            @FormParam("name") String name,
            @NotEmpty
            @Size(min = 1, max = 40)
            @FormParam("teamPrincipal") String teamPrincipal,
            @NotEmpty
            @Size(min = 1, max = 40)
            @FormParam("engine") String engine,
            @NotEmpty
            @Size(min = 1, max = 40)
            @FormParam("chassis") String chassis,

            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @NotEmpty
            @FormParam("seasonUUID") String seasonUUID

    ) {
        int httpStatus = 400;
        String entity = "faield";

        if (DataHandler.readSeasonByUUID(seasonUUID) != null) {
            Team team = new Team();
            team.setTeamUUID(UUID.randomUUID().toString());
            team.setName(name);
            team.setTeamPrincipal(teamPrincipal);
            team.setEngine(engine);
            team.setChassis(chassis);
            team.setSeasonUUID(seasonUUID);

            DataHandler.insertTeam(team);

            httpStatus = 200;
            entity = "Driver successfully inserted";
        }


        return Response
                .status(httpStatus)
                .entity(entity)
                .build();
    }

    /**
     * deletes a team
     *
     * @param teamUUID
     */
    @Path("delete")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteTeam(
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @NotEmpty
            @QueryParam("uuid") String teamUUID
    ) {
        int httpStatus = 400;
        String entity = "faild";

        if (DataHandler.readTeamByUUID(teamUUID) != null) {
            DataHandler.deleteTeam(teamUUID);
            httpStatus = 200;
            entity = "Team erfolgreich gelöscht";
        }

        return Response
                .status(httpStatus)
                .entity(entity)
                .build();
    }

    /**
     * updates a team
     *
     * @param t
     */
    @Path("update")
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateTeam(
            @Valid @BeanParam Team t,
            @NotEmpty
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("seasonUUID") String seasonUUID
    ) {
        int httpStatus = 400;
        String entity = "faild";

        if (DataHandler.readSeasonByUUID(seasonUUID) != null) {
            Team team = DataHandler.readTeamByUUID(t.getTeamUUID());
            if (team != null) {
                team.setTeamUUID(t.getTeamUUID());
                team.setName(t.getName());
                team.setTeamPrincipal(t.getTeamPrincipal());
                team.setEngine(t.getEngine());
                team.setChassis(t.getChassis());
                team.setSeasonUUID(seasonUUID);

                DataHandler.updateTeam();
                httpStatus = 200;
                entity = "Team erfolgreich angelegt";
            }
        }

        return Response
                .status(httpStatus)
                .entity(entity)
                .build();
    }
}
