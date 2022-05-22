package ch.formula.one.model;

public class Fahrer {
    private String fahrerUUID;
    private String name;
    private String vorname;
    private Boolean erstFahrer;
    private Integer siege;
    
    public void setSiege(Integer siege) {
        this.siege = siege;
    }

    public void readFahrer(){

    }

    public void listFahrer(){

    }

    public String getFahrerUUID() {
        return fahrerUUID;
    }

    public void setFahrerUUID(String fahrerUUID) {
        this.fahrerUUID = fahrerUUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public Boolean getErstFahrer() {
        return erstFahrer;
    }

    public void setErstFahrer(Boolean erstFahrer) {
        this.erstFahrer = erstFahrer;
    }

    public int getSiege() {
        return siege;
    }

    public void setSiege(int siege) {
        this.siege = siege;
    }
}
