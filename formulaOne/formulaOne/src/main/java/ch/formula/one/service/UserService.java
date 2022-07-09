package ch.formula.one.service;

import ch.formula.one.data.DataHandler;
import ch.formula.one.model.Driver;
import ch.formula.one.model.User;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * login-service
     *
     * @param username the username
     * @param password the password
     * @return Response
     */
    @PermitAll
    @Path("login")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response loginUser(
            @FormParam("username") String username,
            @FormParam("password") String password
    ) {
        int httpStatus = 200;
        User user = DataHandler.readUser(
                username,
                password
        );


        String token = " guest";
        Map<String, Object> claimMap = new HashMap<>();
        int randomWord = 0;
        if (user.getUserRole().equals("guest")) {
            httpStatus = 404;
        } else {
            randomWord = (int) (Math.random() * 5);
            claimMap.put("role", user.getUserRole());
        }
//        token = JWToken.buildToken(user.getUserRole(), 5, claimMap);


        NewCookie roleCookie = new NewCookie(
                "userRole",
                user.getUserRole(),
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        NewCookie wordCookie = new NewCookie(
                "secret",
                randomWord + 1 + "",
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        return Response
                .status(httpStatus)
                .entity(randomWord + 1)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .cookie(roleCookie)
                .cookie(wordCookie)
                .build();
    }
}
