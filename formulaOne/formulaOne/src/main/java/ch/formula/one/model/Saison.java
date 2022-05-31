package ch.formula.one.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * the Saison is one year in Formel 1
 *
 * @author  Magnus Götz
 * @version 1.0
 * @since   2022-05-23
 */
public class Saison {
    private String saisonUUID;
    private String jahr;
    private String gewinner;

    public Saison(){
    }

    /**
     * gets jahr
     *
     * @return value of jahr
     */
    public String getJahr() {
        return jahr;
    }

    /**
     * sets jahr
     *
     * @param jahr the value to set
     */
    public void setJahr(String jahr) {
        this.jahr = jahr;
    }

    /**
     * gets gewinner
     *
     * @return value of gewinner
     */
    public String getGewinner() {
        return gewinner;
    }

    /**
     * sets gewinner
     *
     * @param gewinner the value to set
     */
    public void setGewinner(String gewinner) {
        this.gewinner = gewinner;
    }

    /**
     * gets saisonUUID
     *
     * @return value of saisonUUID
     */
    public String getSaisonUUID() {
        return saisonUUID;
    }

    /**
     * sets saisonUUID
     *
     * @param saisonUUID the value to set
     */
    public void setSaisonUUID(String saisonUUID) {
        this.saisonUUID = saisonUUID;
    }
}
