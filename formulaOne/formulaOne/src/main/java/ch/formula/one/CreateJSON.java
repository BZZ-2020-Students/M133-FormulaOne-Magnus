package ch.formula.one;

import ch.formula.one.model.Driver;
import ch.formula.one.model.Season;
import ch.formula.one.model.Team;
import ch.formula.one.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;

public class CreateJSON {
    public static void main(String[] args) {
        Season[] seasons = {
                new Season("86b51fbf-7723-4a97-996b-a61efbf7e997", "2022", "-"),
                new Season("ac4d2ae3-b0d4-479d-90db-00e68b1cf447", "2001", "Michael Schumacher"),
                new Season("891dcb15-9058-4f12-8f00-4b572b366dd9", "2000", "Michael Schumacher")
        };

        Team[] teams = {
                new Team("f2095f89-4a09-40b7-80b7-31915f629d93", "Mercedes-AMG Petronas F1 Team", "Mercedes-AMG F1 W13 E Performance","Mercedes-AMG M13","Toto Wolf",seasons[0])
        };

        Driver[] drivers = {
                new Driver("4bf6862d-5ef4-4564-82e6-3ee7ed61fc94","Hamilton","Lewis",true,103, teams[0]),
                new Driver("18cac1b8-c463-41d6-ae9a-a2153e51befc","Russell","George",false,0, teams[0])
        };

        User[] users = {
                new User("7c7ea8ed-2f4a-4943-9a76-bd19bdf984cf","admin","passwort","admin"),
                new User("7c7ea8ed-2f4a-4943-9a76-bd19bdf984cf", "user", "passwort2", "user")

        };


        ObjectMapper om = new ObjectMapper();
        try {
            om.writeValue(Paths.get("C:\\Users\\MGGG\\Documents\\GitHub\\M133-FormulaOne-Magnus\\formulaOne\\formulaOne\\src\\main\\resources\\season.json").toFile(), seasons);
            om.writeValue(Paths.get("C:\\Users\\MGGG\\Documents\\GitHub\\M133-FormulaOne-Magnus\\formulaOne\\formulaOne\\src\\main\\resources\\team.json").toFile(), teams);
            om.writeValue(Paths.get("C:\\Users\\MGGG\\Documents\\GitHub\\M133-FormulaOne-Magnus\\formulaOne\\formulaOne\\src\\main\\resources\\driver.json").toFile(), drivers);
            om.writeValue(Paths.get("C:\\Users\\MGGG\\Documents\\GitHub\\M133-FormulaOne-Magnus\\formulaOne\\formulaOne\\src\\main\\resources\\user.json").toFile(), users);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
