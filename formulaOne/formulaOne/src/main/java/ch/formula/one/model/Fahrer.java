package ch.formula.one.model;

import ch.formula.one.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * A Formel 1 Driver
 *
 * @author Magnus Götz
 * @version 1.0
 * @since 2022-05-23
 */
public class Fahrer {
    private String fahrerUUID;
    private String name;
    private String vorname;
    private Boolean erstFahrer;         //Team Order who is preferred to win
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
        Team team = DataHandler.readTeamByUUID(teamUUID);
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

    public Fahrer(){
    }

    /**
     * gets fahrerUUID
     *
     * @return value of fahrerUUID
     */
    public String getFahrerUUID() {
        return fahrerUUID;
    }

    /**
     * sets fahrerUUID
     *
     * @param fahrerUUID the value to set
     */
    public void setFahrerUUID(String fahrerUUID) {
        this.fahrerUUID = fahrerUUID;
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
     * gets erstFahrer
     *
     * @return value of erstFahrer
     */
    public Boolean getErstFahrer() {
        return erstFahrer;
    }

    /**
     * sets erstFahrer
     *
     * @param erstFahrer the value to set
     */
    public void setErstFahrer(Boolean erstFahrer) {
        this.erstFahrer = erstFahrer;
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
