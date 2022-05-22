package ch.formula.one.model;

import ch.formula.one.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

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


    public String getSaisonUUID() {
        return getSaison().getSaisonUUID();
    }

    public void setSaisonUUID(String saisonUUID) {
        setSaison( new Saison());
        Saison saison = DataHandler.getInstance().readSaisonByUUID(saisonUUID);
        getSaison().setSaisonUUID(saisonUUID);
        getSaison().setSaisonUUID(saison.getSaisonUUID());

    }

    public List<Fahrer> getFahrerList() {
        return fahrerList;
    }

    public void setFahrerList(List<Fahrer> fahrerList) {
        this.fahrerList = fahrerList;
    }

    public List<String> getFahrerUUID() {
        List<String> uuids = new ArrayList<>();
        if(fahrerList != null){
            for (Fahrer fahrer : fahrerList) {
                uuids.add(fahrer.getFahrerUUID());
            }
        }
        return uuids;
    }

    public void setFahrerUUID(List<String> fahrerUUIDs) {
        fahrerList = new ArrayList<>();
        if(fahrerUUIDs != null) {
            for (String fahrerUUID : fahrerUUIDs) {
                Fahrer fahrer = DataHandler.getInstance().readFahrerByUUID(fahrerUUID);
                fahrerList.add(fahrer);
            }
        }


    }

    public String getTeamUUID() {
        return teamUUID;
    }

    public void setTeamUUID(String teamUUID) {
        this.teamUUID = teamUUID;
    }

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

    public Saison getSaison() {
        return saison;
    }

    public void setSaison(Saison saison) {
        this.saison = saison;
    }
}
