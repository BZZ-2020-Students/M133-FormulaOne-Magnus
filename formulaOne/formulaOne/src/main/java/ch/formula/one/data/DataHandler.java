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
    private static List<Driver> driverList;
    private static List<Season> seasonList;
    private static List<Team> teamList;
    private static List<User> userList;

    private static String secret;

    static {
        setSeasonList(new ArrayList<>());
        setTeamList(new ArrayList<>());
        setDriverList(new ArrayList<>());
        setUserList(new ArrayList<>());
        readSeasonJSON();
        readTeamJSON();
        readDriverJSON();
        readUserJSON();
    }

    /*********************************************User***********************************************/

    /**
     * reads all Users
     *
     * @return list of Users
     */
    public static List<User> readAllUser() {
        return getUserList();
    }

    public static User readUser(String username, String password){
        User user = null;

        for (User checkUser : getUserList()) {
            if (checkUser.getUserName().equals(username)&&checkUser.getPassword().equals(password))
                user = checkUser;
        }

        return user;
    }

    /**
     * reads a User by its uuid
     *
     * @param userUUID
     * @return the User (null=not found)
     */
    public static User readUserByUUID(String userUUID) {
        User user = null;
        for (User entry : getUserList()) {
            if (entry.getUserUUID().equals(userUUID)) {
                user = entry;
            }
        }
        return user;
    }

    /**
     * reads the users from the JSON-file
     */
    private static void readUserJSON() {
        try {
            String path = Config.getProperty("userJSON");

            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            User[] users = objectMapper.readValue(jsonData, User[].class);
            for (User user : users) {
                getUserList().add(user);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void insertUser(User user) {
        getUserList().add(user);
        writeUserJSON();
    }

    public static void deleteUser(String userUUID){
        getUserList().remove(readUserByUUID(userUUID));
        writeUserJSON();
    }

    public static void updateUser(){
        writeUserJSON();
    }


    /*********************************************Driver*********************************************/

    /**
     * reads all Drivers
     *
     * @return list of Drivers
     */
    public static List<Driver> readAllDrivers() {
        return getDriverList();
    }

    /**
     * reads a Driver by its uuid
     *
     * @param driverUUID
     * @return the Driver (null=not found)
     */
    public static Driver readDriverByUUID(String driverUUID) {
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
    private static void readDriverJSON() {
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

    public static void insertDriver(Driver driver) {
        getDriverList().add(driver);
        writeDriverJSON();
    }

    public static void deleteDriver(String driverUUID){
        getDriverList().remove(readDriverByUUID(driverUUID));
        writeDriverJSON();
    }

    public static void updateDriver(){
        writeDriverJSON();
    }

    /*********************************************TEAM*********************************************/

    /**
     * reads all Teams
     *
     * @return list of teams
     */
    public static List<Team> readAllTeams() {

        return getTeamList();
    }

    /**
     * reads a team by its uuid
     *
     * @param teamUUID
     * @return the Team (null=not found)
     */
    public static Team readTeamByUUID(String teamUUID) {
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
    private static void readTeamJSON() {
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

    public static void insertTeam(Team team) {
        getTeamList().add(team);
        writeTeamJSON();
    }

    public static void deleteTeam(String driverUUID){
        getTeamList().remove(readTeamByUUID(driverUUID));
        writeTeamJSON();
    }

    public static void updateTeam(){
        writeTeamJSON();
    }

    /*********************************************Season*********************************************/
    /**
     * reads all seasons
     *
     * @return list of seasons
     */
    public static List<Season> readAllSeasons() {
        return getSeasonList();
    }

    /**
     * reads a season by its uuid
     *
     * @param seasonUUID
     * @return the Season (null=not found)
     */
    public static Season readSeasonByUUID(String seasonUUID) {
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
    public static void insertSeason(Season season) {
        getSeasonList().add(season);
        writeSeasonJSON();
    }

    public static void deleteSeason(String seasonUUID){
        getSeasonList().remove(readSeasonByUUID(seasonUUID));
        writeSeasonJSON();
    }

    public static void updateSeason(){
        writeSeasonJSON();
    }

    /**
     * reads the seasons from the JSON-file
     */
    private static void readSeasonJSON() {
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
     * gets userList
     *
     * @return value of userList
     */
    private static List<User> getUserList() {
        return userList;
    }

    /**
     * sets userList
     *
     * @param userList the value to set
     */
    private static void setUserList(List<User> userList) {
        DataHandler.userList = userList;
    }



    /**
     * gets driverList
     *
     * @return value of driverList
     */
    private static List<Driver> getDriverList() {
        return driverList;
    }

    /**
     * sets driverList
     *
     * @param driverList the value to set
     */
    private static void setDriverList(List<Driver> driverList) {
        DataHandler.driverList = driverList;
    }


    /**
     * gets teamList
     *
     * @return value of teamList
     */
    private static List<Team> getTeamList() {
        return teamList;
    }

    /**
     * sets teamList
     *
     * @param teamList the value to set
     */
    private static void setTeamList(List<Team> teamList) {
        DataHandler.teamList = teamList;
    }

    /**
     * gets seasonList
     *
     * @return value of seasonList
     */
    private static List<Season> getSeasonList() {
        return seasonList;
    }

    /**
     * sets seasonList
     *
     * @param seasonList the value to set
     */
    private static void setSeasonList(List<Season> seasonList) {
        DataHandler.seasonList = seasonList;
    }

    private static void writeSeasonJSON() {
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

    private static void writeUserJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String driverPath = Config.getProperty("userJSON");
        try {
            fileOutputStream = new FileOutputStream(driverPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getUserList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void writeDriverJSON() {
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

    private static void writeTeamJSON() {
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
