package ch.formula.one.model;

/**
 * the Season is one year in Formel 1
 *
 * @author  Magnus Götz
 * @version 1.0
 * @since   2022-05-23
 */
public class Season {
    private String seasonUUID;
    private String jahr;
    private String gewinner;

    public Season(){
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
     * gets seasonUUID
     *
     * @return value of seasonUUID
     */
    public String getSeasonUUID() {
        return seasonUUID;
    }

    /**
     * sets seasonUUID
     *
     * @param seasonUUID the value to set
     */
    public void setSeasonUUID(String seasonUUID) {
        this.seasonUUID = seasonUUID;
    }
}
