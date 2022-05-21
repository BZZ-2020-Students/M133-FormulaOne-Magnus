package ch.formula.one.model;

public class Team {
    private String bezeichnung;
    private String teamchef;
    private String motor;
    private String chassis;

    public void readRennstrecke(){

    }

    public void listRennstrecke(){

    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getTeamchef() {
        return teamchef;
    }

    public void setTeamchef(String teamchef) {
        this.teamchef = teamchef;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }
}
