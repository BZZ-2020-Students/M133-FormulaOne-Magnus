package ch.formula.one.model;

/**
 * User Data for Login
 *
 * @author Magnus Götz
 * @version 1.0
 * @since 2022-05-23
 */
public class User {
    private String userUUID;
    private String userName;
    private String password;
    private String userRole;

    /**
     * gets userUUID
     *
     * @return value of userUUID
     */
    public String getUserUUID() {
        return userUUID;
    }

    /**
     * sets userUUID
     *
     * @param userUUID the value to set
     */
    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    /**
     * gets userName
     *
     * @return value of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * sets userName
     *
     * @param userName the value to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * gets password
     *
     * @return value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets password
     *
     * @param password the value to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * gets userRole
     *
     * @return value of userRole
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * sets userRole
     *
     * @param userRole the value to set
     */
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}