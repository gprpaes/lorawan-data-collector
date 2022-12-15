package br.ufrrj.labsd.mongo;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("mongo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MongoResource {
    MongoRepository mongoRepository = new MongoRepository();
    MongoService mongoService = new MongoService(mongoRepository);

    @POST
    public Response createMongoInstance(MongoModel mongoModel){
        System.out.println("Teste" + mongoModel.toString());
        return Response.accepted(mongoModel).build();
    }


}
