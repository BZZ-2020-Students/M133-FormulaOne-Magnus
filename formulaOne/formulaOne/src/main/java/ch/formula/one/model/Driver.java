package ch.formula.one.model;

import ch.formula.one.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import jakarta.ws.rs.FormParam;

/**
 * A Formel 1 Driver
 *
 * @author Magnus Götz
 * @version 1.0
 * @since 2022-05-23
 */
public class Driver {
    @FormParam("driverUUID")
    @NotEmpty
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String driverUUID;
    @FormParam("name")
    @NotEmpty
    @Size(min=1, max=40)
    private String name;
    @FormParam("firstname")
    @NotEmpty
    @Size(min=1, max=40)
    private String firstname;
    @FormParam("firstDriver")
    @NotNull
    private Boolean firstDriver;         //Team Order who is preferred to win
    @FormParam("wins")
    @Max(999)
    @Min(0)
    private Integer wins;
    
    @JsonIgnore
    private Team team;

    /**
     * gets teamUUID
     *
     * @return teamUUID
     */
    public String getTeamUUID() {
        return getTeam().getTeamUUID();
    }

    /**
     * creates a Team by teamUUID
     *
     * @param teamUUID
     */
    public void setTeamUUID(String teamUUID) {
        setTeam(new Team());
        Team team = DataHandler.readTeamByUUID(teamUUID);
        getTeam().setTeamUUID(team.getTeamUUID());
        getTeam().setName(team.getName());
        getTeam().setChassis(team.getChassis());
        getTeam().setEngine(team.getEngine());
        getTeam().setTeamPrincipal(team.getTeamPrincipal());

        getTeam().setSeasonUUID(team.getSeasonUUID());

    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Driver(){
    }

    /**
     * gets driverUUID
     *
     * @return value of driverUUID
     */
    public String getDriverUUID() {
        return driverUUID;
    }

    /**
     * sets driverUUID
     *
     * @param driverUUID the value to set
     */
    public void setDriverUUID(String driverUUID) {
        this.driverUUID = driverUUID;
    }

    /**
     * gets name
     *
     * @return value of name
     */
    public String getName() {
        return name;
    }

    /**
     * sets name
     *
     * @param name the value to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets firstname
     *
     * @return value of firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * sets firstname
     *
     * @param firstname the value to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * gets firstDriver
     *
     * @return value of firstDriver
     */
    public Boolean getFirstDriver() {
        return firstDriver;
    }

    /**
     * sets firstDriver
     *
     * @param firstDriver the value to set
     */
    public void setFirstDriver(Boolean firstDriver) {
        this.firstDriver = firstDriver;
    }

    /**
     * gets wins
     *
     * @return value of wins
     */
    public int getWins() {
        return wins;
    }

    /**
     * sets wins
     *
     * @param wins the value to set
     */
    public void setWins(Integer wins) {
        this.wins = wins;
    }
}
