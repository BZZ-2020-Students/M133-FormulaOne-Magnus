package ch.formula.one.model;

import ch.formula.one.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * the Team has Drivers for a Season
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
//    @JsonIgnore
//    private List<Fahrer> fahrerList;
    @JsonIgnore
    private Season season;

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
        Season season = DataHandler.getInstance().readSeasonByUUID(seasonUUID);
        getSeason().setSeasonUUID(seasonUUID);
        getSeason().setSeasonUUID(season.getSeasonUUID());
        getSeason().setJahr(season.getJahr());
        getSeason().setGewinner(season.getGewinner());

    }

//    /***
//     * gets List of fahrerUUIDs
//     * @return List of fahrerUUIDs
//     */
//    public List<String> getFahrerUUID() {
//        List<String> uuids = new ArrayList<>();
//        if (fahrerList != null) {
//            for (Fahrer fahrer : fahrerList) {
//                uuids.add(fahrer.getFahrerUUID());
//            }
//        }
//        return uuids;
//    }
//
//    /**
//     * creates a List of Fahrers by fahrerUUIDs
//     *
//     * @param fahrerUUIDs
//     */
//    public void setFahrerUUID(List<String> fahrerUUIDs) {
//        fahrerList = new ArrayList<>();
//        if (fahrerUUIDs != null) {
//            for (String fahrerUUID : fahrerUUIDs) {
//                Fahrer fahrer = DataHandler.getInstance().readFahrerByUUID(fahrerUUID);
//                fahrerList.add(fahrer);
//            }
//        }
//    }

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

//    /**
//     * gets fahrerList
//     *
//     * @return value of fahrerList
//     */
//    public List<Fahrer> getFahrerList() {
//        return fahrerList;
//    }
//
//    /**
//     * sets fahrerList
//     *
//     * @param fahrerList the value to set
//     */
//    public void setFahrerList(List<Fahrer> fahrerList) {
//        this.fahrerList = fahrerList;
//    }

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
