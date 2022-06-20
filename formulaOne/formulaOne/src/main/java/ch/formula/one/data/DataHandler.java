package ch.formula.one.data;

import ch.formula.one.model.Driver;
import ch.formula.one.model.Season;
import ch.formula.one.model.Team;
import ch.formula.one.model.User;
import ch.formula.one.service.Config;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {
    private static DataHandler instance = null;
    private List<Driver> driverList;
    private List<Season> seasonList;
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
        setSeasonList(new ArrayList<>());
        setTeamList(new ArrayList<>());
        setDriverList(new ArrayList<>());
        readSeasonJSON();
        readTeamJSON();
        readDriverJSON();
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


    /*********************************************Driver*********************************************/

    /**
     * reads all Drivers
     *
     * @return list of Drivers
     */
    public List<Driver> readAllDrivers() {
        return getDriverList();
    }

    /**
     * reads a Driver by its uuid
     *
     * @param driverUUID
     * @return the Driver (null=not found)
     */
    public Driver readDriverByUUID(String driverUUID) {
        Driver driver = null;
        for (Driver entry : getDriverList()) {
            if (entry.getDriverUUID().equals(driverUUID)) {
                driver = entry;
            }
        }
        return driver;
    }

    /**
     * reads the drivers from the JSON-file
     */
    private void readDriverJSON() {
        try {
            String path = Config.getProperty("driverJSON");

            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Driver[] drivers = objectMapper.readValue(jsonData, Driver[].class);
            for (Driver driver : drivers) {
                getDriverList().add(driver);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void insertDriver(Driver driver) {
        getDriverList().add(driver);
        writeDriverJSON();
    }

    public void deleteDriver(String driverUUID){
        getSeasonList().remove(readDriverByUUID(driverUUID));
        writeDriverJSON();
    }

    public void updateDriver(){
        writeDriverJSON();
    }

    /*********************************************TEAM*********************************************/

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

    public void insertTeam(Team team) {
        getTeamList().add(team);
        writeTeamJSON();
    }

    public void deleteTeam(String driverUUID){
        getSeasonList().remove(readTeamByUUID(driverUUID));
        writeTeamJSON();
    }

    public void updateTeam(){
        writeDriverJSON();
    }

    /*********************************************Season*********************************************/
    /**
     * reads all seasons
     *
     * @return list of seasons
     */
    public List<Season> readAllSeasons() {
        return getSeasonList();
    }

    /**
     * reads a season by its uuid
     *
     * @param seasonUUID
     * @return the Season (null=not found)
     */
    public Season readSeasonByUUID(String seasonUUID) {
        Season season = null;
        for (Season entry : getSeasonList()) {
            if (entry.getSeasonUUID().equals(seasonUUID)) {
                season = entry;
            }
        }
        return season;
    }

    /**
     * insert a season
     *
     * @param season
     */
    public void insertSeason(Season season) {
        getSeasonList().add(season);
        writeSeasonJSON();
    }

    public void deleteSeason(String seasonUUID){
        getSeasonList().remove(readSeasonByUUID(seasonUUID));
        writeSeasonJSON();
    }

    public void updateSeason(){
        writeSeasonJSON();
    }

    /**
     * reads the seasons from the JSON-file
     */
    private void readSeasonJSON() {
        try {
            String path = Config.getProperty("seasonJSON");

            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Season[] seasons = objectMapper.readValue(jsonData, Season[].class);
            for (Season season : seasons) {
                getSeasonList().add(season);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * gets driverList
     *
     * @return value of driverList
     */
    private List<Driver> getDriverList() {
        return driverList;
    }

    /**
     * sets driverList
     *
     * @param driverList the value to set
     */
    private void setDriverList(List<Driver> driverList) {
        this.driverList = driverList;
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
     * gets seasonList
     *
     * @return value of seasonList
     */
    private List<Season> getSeasonList() {
        return seasonList;
    }

    /**
     * sets seasonList
     *
     * @param seasonList the value to set
     */
    private void setSeasonList(List<Season> seasonList) {
        this.seasonList = seasonList;
    }

    private void writeSeasonJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String seasonPath = Config.getProperty("seasonJSON");
        try {
            fileOutputStream = new FileOutputStream(seasonPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getSeasonList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void writeDriverJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String driverPath = Config.getProperty("driverJSON");
        try {
            fileOutputStream = new FileOutputStream(driverPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getDriverList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void writeTeamJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String teamPath = Config.getProperty("teamJSON");
        try {
            fileOutputStream = new FileOutputStream(teamPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getTeamList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
