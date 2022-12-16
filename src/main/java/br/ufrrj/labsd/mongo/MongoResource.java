package br.ufrrj.labsd.mongo;

import br.ufrrj.labsd.database.DatabaseService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.net.URISyntaxException;


@Path("mongo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MongoResource {
    MongoRepository mongoRepository = new MongoRepository();
    MongoService mongoService = new MongoService(mongoRepository);

    @POST
    public Response createMongoInstance(MongoModel mongoModel) throws URISyntaxException {
        Integer created = mongoService.createMongoInstance(mongoModel);
        Response response;
        if(created == null) response = Response.serverError().build();
        else{
            //TODO: PEGAR URL DO CONTEXTO
            response = Response.created(new URI("http://localhost:8086/"+created)).build();
        }
        return response;

    }


}
