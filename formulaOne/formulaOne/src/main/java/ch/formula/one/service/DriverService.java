package ch.formula.one.service;

import ch.formula.one.data.DataHandler;
import ch.formula.one.model.Driver;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

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
}
