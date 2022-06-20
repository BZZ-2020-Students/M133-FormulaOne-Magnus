package ch.formula.one.service;

import ch.formula.one.data.DataHandler;
import ch.formula.one.model.Driver;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

/**
 * services to list and read Drivers
 *
 * @author Magnus Götz
 * @version 1.0
 * @since 2022-05-23
 */
@Path("driver")
public class DriverService {
    /**
     * reads a list of driver
     *
     * @return driver
     */
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listDrivers() {
        List<Driver> driverList = DataHandler.getInstance().readAllDrivers();
        Response response = Response
                .status(200)
                .entity(driverList)
                .build();
        return response;
    }

    /**
     * reads a driver identified by the uuid
     *
     * @param driverUUID
     * @return driver
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readDriver(
            @QueryParam("uuid") String driverUUID
    ) {
        int httpStatus = 200;
        Driver driver = DataHandler.getInstance().readDriverByUUID(driverUUID);
        if (driver == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(driver)
                .build();
    }

    /**
     * creates a driver
     *
     * @param name
     * @param firstname
     * @param firstDriver
     * @param wins
     * @param teamUUID
     */
    @Path("create")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertDriver(
            @NotEmpty
            @Size(min=1, max=40)
            @FormParam("name") String name,
            @NotEmpty
            @Size(min=1, max=40)
            @FormParam("firstname") String firstname,
            @NotEmpty
            @FormParam("firstDriver") Boolean firstDriver,
            @Max(999)
            @Min(0)
            @FormParam("wins") Integer wins,
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @NotEmpty
            @FormParam("teamUUID") String teamUUID

    ) {
        Driver driver = new Driver();
        driver.setDriverUUID(UUID.randomUUID().toString());
        driver.setName(name);
        driver.setFirstname(firstname);
        driver.setFirstDriver(firstDriver);
        driver.setWins(wins);
        driver.setTeamUUID(teamUUID);

        DataHandler.getInstance().insertDriver(driver);

        int httpStatus = 200;
        return Response
                .status(httpStatus)
                .entity("Driver successfully inserted")
                .build();
    }

    /**
     * deletes a driver identified by the uuid
     *
     * @param driverUUID
     */
    @Path("delete")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertDriver(
            @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @NotEmpty
            @QueryParam("uuid") String driverUUID
    ) {
        DataHandler.getInstance().deleteDriver(driverUUID);

        int httpStatus = 200;
        return Response
                .status(httpStatus)
                .entity("Driver successfully deleted")
                .build();
    }

    /**
     * updates a driver
     *
     * @param d
     */
    @Path("update")
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateDriver(
            @Valid @BeanParam Driver d
    ) {
        Driver driver = DataHandler.getInstance().readDriverByUUID(d.getDriverUUID());
        driver.setDriverUUID(d.getDriverUUID());
        driver.setName(d.getName());
        driver.setFirstname(d.getFirstname());
        driver.setFirstDriver(d.getFirstDriver());
        driver.setWins(d.getWins());
        driver.setTeamUUID(d.getTeamUUID());

        DataHandler.getInstance().updateDriver();

        int httpStatus = 200;
        return Response
                .status(httpStatus)
                .entity("Driver successfully updated")
                .build();
    }
}
