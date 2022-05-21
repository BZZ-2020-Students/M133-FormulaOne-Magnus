package ch.formula.one.model;

import java.time.LocalDate;

public class Saison {
    private String saisonUUID;
    private LocalDate jahr;
    private String gewinner;

    public void readSaison(){

    }

    public void listSaison(){

    }

    public LocalDate getJahr() {
        return jahr;
    }

    public void setJahr(LocalDate jahr) {
        this.jahr = jahr;
    }

    public String getGewinner() {
        return gewinner;
    }

    public void setGewinner(String gewinner) {
        this.gewinner = gewinner;
    }

    public String getSaisonUUID() {
        return saisonUUID;
    }

    public void setSaisonUUID(String saisonUUID) {
        this.saisonUUID = saisonUUID;
    }
}
