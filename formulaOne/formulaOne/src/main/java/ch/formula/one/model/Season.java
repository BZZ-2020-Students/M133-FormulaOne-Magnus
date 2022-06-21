package ch.formula.one.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.FormParam;

/**
 * the Season is one year in Formel 1
 *
 * @author  Magnus Götz
 * @version 1.0
 * @since   2022-05-23
 */
public class Season {
    @FormParam("seasonUUID")
    @NotEmpty
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String seasonUUID;
    @FormParam("year")
    @NotEmpty
    @Size(min=4, max=4)
    private String year;
    @FormParam("winner")
    @NotEmpty
    @Size(min=1, max=40)
    private String winner;

    public Season(){
    }

    /**
     * gets year
     *
     * @return value of year
     */
    public String getYear() {
        return year;
    }

    /**
     * sets year
     *
     * @param year the value to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * gets winner
     *
     * @return value of winner
     */
    public String getWinner() {
        return winner;
    }

    /**
     * sets winner
     *
     * @param winner the value to set
     */
    public void setWinner(String winner) {
        this.winner = winner;
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
