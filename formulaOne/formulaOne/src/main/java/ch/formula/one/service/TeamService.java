package ch.formula.one.service;

import ch.formula.one.data.DataHandler;
import ch.formula.one.model.Driver;
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
        List<Team> teamList = DataHandler.getInstance().readAllTeams();
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
        Team team = DataHandler.getInstance().readTeamByUUID(teamUUID);
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
    public Response insertDriver(
            @NotEmpty
            @Size(min=1, max=40)
            @FormParam("name") String name,
            @NotEmpty
            @Size(min=1, max=40)
            @FormParam("teamPrincipal") String teamPrincipal,
            @NotEmpty
            @Size(min=1, max=40)
            @FormParam("engine") String engine,
            @NotEmpty
            @Size(min=1, max=40)
            @FormParam("chassis") String chassis,

            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @NotEmpty
            @FormParam("seasonUUID") String seasonUUID

    ) {
        Team team = new Team();
        team.setTeamUUID(UUID.randomUUID().toString());
        team.setName(name);
        team.setTeamPrincipal(teamPrincipal);
        team.setEngine(engine);
        team.setChassis(chassis);
        team.setSeasonUUID(seasonUUID);

        DataHandler.getInstance().insertTeam(team);

        int httpStatus = 200;
        return Response
                .status(httpStatus)
                .entity("Team erfolgreich angelegt")
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
    public Response insertDriver(
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @NotEmpty
            @QueryParam("uuid") String teamUUID
    ) {
        DataHandler.getInstance().deleteTeam(teamUUID);

        int httpStatus = 200;
        return Response
                .status(httpStatus)
                .entity("Team erfolgreich gelöscht")
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
    public Response updateDriver(
            @Valid @BeanParam Team t
    ) {
        Team team = DataHandler.getInstance().readTeamByUUID(t.getTeamUUID());
        team.setTeamUUID(t.getTeamUUID());
        team.setName(t.getName());
        team.setTeamPrincipal(t.getTeamPrincipal());
        team.setEngine(t.getEngine());
        team.setChassis(t.getChassis());
        team.setSeasonUUID(t.getSeasonUUID());

        DataHandler.getInstance().updateTeam();

        int httpStatus = 200;
        return Response
                .status(httpStatus)
                .entity("Team erfolgreich angelegt")
                .build();
    }
}
