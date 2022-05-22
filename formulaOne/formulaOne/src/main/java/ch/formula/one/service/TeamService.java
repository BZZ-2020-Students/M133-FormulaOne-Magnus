package ch.formula.one.service;

import ch.formula.one.data.DataHandler;
import ch.formula.one.model.Team;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("team")
public class TeamService {

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
}
