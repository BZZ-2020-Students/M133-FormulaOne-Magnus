package ch.formula.one.service;

import ch.formula.one.data.DataHandler;
import ch.formula.one.model.Driver;
import ch.formula.one.model.User;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * services for Users
 *
 * @author Magnus Götz
 * @version 1.0
 * @since 2022-05-23
 */
@Path("user")
public class UserService {
    /**
     * reads a list of user
     *
     * @return user
     */
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listDrivers() {
        List<User> userList = DataHandler.readAllUser();
        Response response = Response
                .status(200)
                .entity(userList)
                .build();
        return response;
    }
}
