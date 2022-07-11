package ch.formula.one.model;

import ch.formula.one.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.FormParam;

/**
 * the Team has Drivers for a Season
 *
 * @author Magnus Götz
 * @version 1.0
 * @since 2022-05-23
 */
public class Team {
    @FormParam("teamUUID")
    @NotEmpty
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String teamUUID;
    @FormParam("name")
    @NotEmpty
    @Size(min=1, max=40)
    private String name;
    @FormParam("teamPrincipal")
    @NotEmpty
    @Size(min=1, max=40)
    private String teamPrincipal;
    @FormParam("engine")
    @NotEmpty
    @Size(min=1, max=40)
    private String engine;
    @FormParam("chassis")
    @NotEmpty
    @Size(min=1, max=40)
    private String chassis;
    @JsonIgnore
    private Season season;

    public Team() {
    }

    public Team(String teamUUID, String name, String teamPrincipal, String engine, String chassis, Season season) {
        this.teamUUID = teamUUID;
        this.name = name;
        this.teamPrincipal = teamPrincipal;
        this.engine = engine;
        this.chassis = chassis;
        this.season = season;
    }

    /**
     * gets seasonUUID
     *
     * @return seasonUUID
     */
    public String getSeasonUUID() {
        return getSeason().getSeasonUUID();
    }

    /**
     * creates a Season by seasonUUID
     *
     * @param seasonUUID
     */
    public void setSeasonUUID(String seasonUUID) {
        setSeason(new Season());
        Season season = DataHandler.readSeasonByUUID(seasonUUID);
        getSeason().setSeasonUUID(seasonUUID);
        getSeason().setSeasonUUID(season.getSeasonUUID());
        getSeason().setYear(season.getYear());
        getSeason().setWinner(season.getWinner());
    }

    /**
     * gets teamUUID
     *
     * @return value of teamUUID
     */
    public String getTeamUUID() {
        return teamUUID;
    }

    /**
     * sets teamUUID
     *
     * @param teamUUID the value to set
     */
    public void setTeamUUID(String teamUUID) {
        this.teamUUID = teamUUID;
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
     * gets teamPrincipal
     *
     * @return value of teamPrincipal
     */
    public String getTeamPrincipal() {
        return teamPrincipal;
    }

    /**
     * sets teamPrincipal
     *
     * @param teamPrincipal the value to set
     */
    public void setTeamPrincipal(String teamPrincipal) {
        this.teamPrincipal = teamPrincipal;
    }

    /**
     * gets engine
     *
     * @return value of engine
     */
    public String getEngine() {
        return engine;
    }

    /**
     * sets engine
     *
     * @param engine the value to set
     */
    public void setEngine(String engine) {
        this.engine = engine;
    }

    /**
     * gets chassis
     *
     * @return value of chassis
     */
    public String getChassis() {
        return chassis;
    }

    /**
     * sets chassis
     *
     * @param chassis the value to set
     */
    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    /**
     * gets season
     *
     * @return value of season
     */
    public Season getSeason() {
        return season;
    }

    /**
     * sets season
     *
     * @param season the value to set
     */
    public void setSeason(Season season) {
        this.season = season;
    }
}
