package ch.formula.one.data;

import ch.formula.one.model.Fahrer;
import ch.formula.one.model.Saison;
import ch.formula.one.model.Team;
import ch.formula.one.model.User;
import ch.formula.one.service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {
    private static DataHandler instance = null;
    private List<Fahrer> fahrerList;
    private List<Saison> saisonList;
    private List<Team> teamList;
    private List<User> userList;

    /**
     * private constructor defeats instantiation
     *
     * @author Magnus Götz
     * @version 1.0
     * @since 2022-05-23
     */
    private DataHandler() {
        setTeamList(new ArrayList<>());
        readTeamJSON();
        setFahrerList(new ArrayList<>());
        readFahrerJSON();
        setSaisonList(new ArrayList<>());
        readSaisonJSON();
    }

    /**
     * gets the only instance of this class
     *
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * reads all fahrers
     *
     * @return list of fahrers
     */
    public List<Fahrer> readAllFahrers() {
        return getFahrerList();
    }

    /**
     * reads a fahrer by its uuid
     *
     * @param fahrerUUID
     * @return the Fahrer (null=not found)
     */
    public Fahrer readFahrerByUUID(String fahrerUUID) {
        Fahrer fahrer = null;
        for (Fahrer entry : getFahrerList()) {
            if (entry.getFahrerUUID().equals(fahrerUUID)) {
                fahrer = entry;
            }
        }
        return fahrer;
    }

    /**
     * reads all Teams
     *
     * @return list of teams
     */
    public List<Team> readAllTeams() {

        return getTeamList();
    }

    /**
     * reads a team by its uuid
     *
     * @param teamUUID
     * @return the Team (null=not found)
     */
    public Team readTeamByUUID(String teamUUID) {
        Team team = null;
        for (Team entry : getTeamList()) {
            if (entry.getTeamUUID().equals(teamUUID)) {
                team = entry;
            }
        }
        return team;
    }

    /**
     * reads all saisons
     *
     * @return list of saisons
     */
    public List<Saison> readAllSaisons() {
        return getSaisonList();
    }

    /**
     * reads a saison by its uuid
     *
     * @param saisonUUID
     * @return the Saison (null=not found)
     */
    public Saison readSaisonByUUID(String saisonUUID) {
        Saison saison = null;
        for (Saison entry : getSaisonList()) {
            if (entry.getSaisonUUID().equals(saisonUUID)) {
                saison = entry;
            }
        }
        return saison;
    }


    /**
     * reads the fahrers from the JSON-file
     */
    private void readFahrerJSON() {
        try {
            String path = Config.getProperty("fahrerJSON");

            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Fahrer[] fahrers = objectMapper.readValue(jsonData, Fahrer[].class);
            for (Fahrer fahrer : fahrers) {
                getFahrerList().add(fahrer);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the teams from the JSON-file
     */
    private void readTeamJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("teamJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Team[] teams = objectMapper.readValue(jsonData, Team[].class);
            for (Team team : teams) {
                getTeamList().add(team);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the saisons from the JSON-file
     */
    private void readSaisonJSON() {
        try {
            String path = Config.getProperty("saisonJSON");

            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Saison[] saisons = objectMapper.readValue(jsonData, Saison[].class);
            for (Saison saison : saisons) {
                getSaisonList().add(saison);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * gets fahrerList
     *
     * @return value of fahrerList
     */
    private List<Fahrer> getFahrerList() {
        return fahrerList;
    }

    /**
     * sets fahrerList
     *
     * @param fahrerList the value to set
     */
    private void setFahrerList(List<Fahrer> fahrerList) {
        this.fahrerList = fahrerList;
    }

    /**
     * gets teamList
     *
     * @return value of teamList
     */
    private List<Team> getTeamList() {
        return teamList;
    }

    /**
     * sets teamList
     *
     * @param teamList the value to set
     */
    private void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    /**
     * gets saisonList
     *
     * @return value of saisonList
     */
    private List<Saison> getSaisonList() {
        return saisonList;
    }

    /**
     * sets saisonList
     *
     * @param saisonList the value to set
     */
    private void setSaisonList(List<Saison> saisonList) {
        this.saisonList = saisonList;
    }
}
