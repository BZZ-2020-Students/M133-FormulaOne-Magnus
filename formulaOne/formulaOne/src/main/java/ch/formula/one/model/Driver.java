package ch.formula.one.model;

import ch.formula.one.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A Formel 1 Driver
 *
 * @author Magnus Götz
 * @version 1.0
 * @since 2022-05-23
 */
public class Driver {
    private String driverUUID;
    private String name;
    private String vorname;
    private Boolean erstDriver;         //Team Order who is preferred to win
    private Integer siege;
    
    @JsonIgnore
    private Team team;

    /**
     * gets personUUID
     *
     * @return personUUID
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
        Team team = DataHandler.getInstance().readTeamByUUID(teamUUID);
        getTeam().setTeamUUID(teamUUID);
        getTeam().setTeamUUID(team.getTeamUUID());
        getTeam().setBezeichnung(team.getBezeichnung());
        getTeam().setChassis(team.getChassis());
        getTeam().setMotor(team.getMotor());
        getTeam().setTeamchef(team.getTeamchef());
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
     * gets vorname
     *
     * @return value of vorname
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * sets vorname
     *
     * @param vorname the value to set
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * gets erstDriver
     *
     * @return value of erstDriver
     */
    public Boolean getErstDriver() {
        return erstDriver;
    }

    /**
     * sets erstDriver
     *
     * @param erstDriver the value to set
     */
    public void setErstDriver(Boolean erstDriver) {
        this.erstDriver = erstDriver;
    }

    /**
     * gets siege
     *
     * @return value of siege
     */
    public int getSiege() {
        return siege;
    }

    /**
     * sets siege
     *
     * @param siege the value to set
     */
    public void setSiege(Integer siege) {
        this.siege = siege;
    }
}
