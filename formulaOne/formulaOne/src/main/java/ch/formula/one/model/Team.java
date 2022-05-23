package ch.formula.one.model;

import ch.formula.one.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * the Team has Drivers for a Saison
 *
 * @author Magnus Götz
 * @version 1.0
 * @since 2022-05-23
 */
public class Team {
    private String teamUUID;
    private String bezeichnung;
    private String teamchef;
    private String motor;
    private String chassis;
    @JsonIgnore
    private List<Fahrer> fahrerList;
    @JsonIgnore
    private Saison saison;

    /**
     * gets personUUID
     *
     * @return personUUID
     */
    public String getSaisonUUID() {
        return getSaison().getSaisonUUID();
    }

    /**
     * creates a Saison by saisonUUID
     *
     * @param saisonUUID
     */
    public void setSaisonUUID(String saisonUUID) {
        setSaison(new Saison());
        Saison saison = DataHandler.getInstance().readSaisonByUUID(saisonUUID);
        getSaison().setSaisonUUID(saisonUUID);
        getSaison().setSaisonUUID(saison.getSaisonUUID());

    }

    /***
     * gets List of fahrerUUIDs
     * @return List of fahrerUUIDs
     */
    public List<String> getFahrerUUID() {
        List<String> uuids = new ArrayList<>();
        if (fahrerList != null) {
            for (Fahrer fahrer : fahrerList) {
                uuids.add(fahrer.getFahrerUUID());
            }
        }
        return uuids;
    }

    /**
     * creates a List of Fahrers by fahrerUUIDs
     *
     * @param fahrerUUIDs
     */
    public void setFahrerUUID(List<String> fahrerUUIDs) {
        fahrerList = new ArrayList<>();
        if (fahrerUUIDs != null) {
            for (String fahrerUUID : fahrerUUIDs) {
                Fahrer fahrer = DataHandler.getInstance().readFahrerByUUID(fahrerUUID);
                fahrerList.add(fahrer);
            }
        }
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
     * gets bezeichnung
     *
     * @return value of bezeichnung
     */
    public String getBezeichnung() {
        return bezeichnung;
    }

    /**
     * sets bezeichnung
     *
     * @param bezeichnung the value to set
     */
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    /**
     * gets teamchef
     *
     * @return value of teamchef
     */
    public String getTeamchef() {
        return teamchef;
    }

    /**
     * sets teamchef
     *
     * @param teamchef the value to set
     */
    public void setTeamchef(String teamchef) {
        this.teamchef = teamchef;
    }

    /**
     * gets motor
     *
     * @return value of motor
     */
    public String getMotor() {
        return motor;
    }

    /**
     * sets motor
     *
     * @param motor the value to set
     */
    public void setMotor(String motor) {
        this.motor = motor;
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
     * gets fahrerList
     *
     * @return value of fahrerList
     */
    public List<Fahrer> getFahrerList() {
        return fahrerList;
    }

    /**
     * sets fahrerList
     *
     * @param fahrerList the value to set
     */
    public void setFahrerList(List<Fahrer> fahrerList) {
        this.fahrerList = fahrerList;
    }

    /**
     * gets saison
     *
     * @return value of saison
     */
    public Saison getSaison() {
        return saison;
    }

    /**
     * sets saison
     *
     * @param saison the value to set
     */
    public void setSaison(Saison saison) {
        this.saison = saison;
    }
}
