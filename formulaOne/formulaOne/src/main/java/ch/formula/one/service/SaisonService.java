package ch.formula.one.service;

import ch.formula.one.data.DataHandler;
import ch.formula.one.model.Saison;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("saison")
public class SaisonService {
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listSaisons() {
        List<Saison> saisonList = DataHandler.getInstance().readAllSaisons();
        Response response = Response
                .status(200)
                .entity(saisonList)
                .build();
        return response;
    }

//    @GET
//    @Path("read")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response readBook(
//            @QueryParam("uuid") String bookUUID
//    ) {
//        int httpStatus = 200;
//        Book book = DataHandler.getInstance().readBookByUUID(bookUUID);
//        if (book == null) {
//            httpStatus = 410;
//        }
//        return Response
//                .status(httpStatus)
//                .entity(book)
//                .build();
//    }
}
