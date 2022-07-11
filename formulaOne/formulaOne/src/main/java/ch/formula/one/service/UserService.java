package ch.formula.one.service;

import ch.formula.one.data.DataHandler;
import ch.formula.one.model.User;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
    public Response listUsers() {
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

        Map<String, Object> claimMap = new HashMap<>();
        int randomInt = 0;
        if (user.getUserRole().equals("guest")) {
            httpStatus = 404;
        } else {
            Random r = new Random();
            randomInt = r.nextInt(1000)+1;
            claimMap.put("role", user.getUserRole());
        }


        NewCookie roleCookie = new NewCookie(
                "userRole",
                user.getUserRole()+"",
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        NewCookie wordCookie = new NewCookie(
                "secret",
                randomInt+"",
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        return Response
                .status(httpStatus)
                .entity(randomInt)
                .cookie(roleCookie)
                .cookie(wordCookie)
                .build();
    }

    @PermitAll
    @Path("2fa")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response checkWord(
            @FormParam("secret") String secret,
            @CookieParam("secret") Cookie cookie
            ) {
        int httpStatus = 200;
        String number = cookie.getValue();
        if (number == null || !number.equals(secret)) {
            httpStatus = 401;
        }

        return Response
                .status(httpStatus)
                .entity(null)
                .build();
    }
}
