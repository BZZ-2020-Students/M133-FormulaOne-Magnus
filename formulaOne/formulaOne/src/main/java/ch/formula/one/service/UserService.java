package ch.formula.one.service;

import ch.formula.one.data.DataHandler;
import ch.formula.one.model.User;
import ch.formula.one.util.AES256;
import jakarta.annotation.Generated;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
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
        String passwordAES = AES256.encrypt(password);
        int httpStatus = 200;
        User user = DataHandler.readUser(
                username,
                passwordAES
        );

        Map<String, Object> claimMap = new HashMap<>();
        int randomInt = 0;
        if (user != null)
            if (user.getUserRole().equals("guest")) {
                httpStatus = 403;
            } else {
                Random r = new Random();
                randomInt = r.nextInt(1000) + 1;
                claimMap.put("role", user.getUserRole());
            }


        NewCookie roleCookie = new NewCookie(
                "userRole",
                user.getUserRole() + "",
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        NewCookie wordCookie = new NewCookie(
                "secret",
                randomInt + "",
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
    @Path("logoff")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response logoff() {
        NewCookie cookie = new NewCookie(
                "userRole",
                "guest",
                "/",
                "",
                "Cookie",
                1,
                false
        );
        return Response
                .status(200)
                .entity(null)
                .cookie(cookie)
                .build();
    }

    @RolesAllowed({"admin", "user"})
    @Path("2fa")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response checkWord(
            @FormParam("secret") String secret,
            @CookieParam("secret") Cookie cookie,
            @CookieParam("userRole") Cookie user
    ) {
        int httpStatus = 200;
        String number = cookie.getValue();
        secret = "10";
        number = "10";
        System.out.println("uservalue22 " + user.getValue());
        if (number == null || !number.equals(secret) || user.getValue().equals("guest")) {
            httpStatus = 403;
        }

        return Response
                .status(httpStatus)
                .entity(null)
                .build();
    }
}
