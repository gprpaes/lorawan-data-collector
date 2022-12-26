package br.ufrrj.labsd.mongo;

import br.ufrrj.labsd.database.DatabaseService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
            // @TODO: PEGAR URL DO CONTEXTO
            response = Response.created(new URI("http://localhost:8086/mongo/"+created)).build();
        }
        return response;
    }

    @GET
    @Path("/{id}")
    public Response getMongoInstanceById(@PathParam("id") int mongoId){
        MongoModel mongo = mongoService.getMongoInstance(mongoId);
        Response response;
        if(mongo == null) response = Response.status(Response.Status.NOT_FOUND).build();
        else response = Response.ok().entity(mongo).build();

        return response;
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMongoInstanceById(@PathParam("id") int mongoId){
        Integer rowCount = mongoService.deleteMongoInstance(mongoId);
        Response response;
        if(rowCount == null) response = Response.serverError().build();
        else{
            Map<String, Integer> rowCountMap = new HashMap<>();
            rowCountMap.put("rowCount", rowCount);
            response = Response.ok().entity(rowCountMap).build();
        }

        return response;
    }

    @GET
    public Response getAllMongoInstances(){
        Response response;
        List<MongoModel> mongoModels = mongoService.getAllMongoInstances();
        if(mongoModels == null) response = Response.serverError().build();
        else {
            response = Response.ok().entity(mongoModels).build();
        }
        return response;
    }

    @PUT
    @Path("/{id}")
    public Response updateMongoInstance(@PathParam("id") Integer id, MongoModel mongoModel){
        Response response;

        boolean result = mongoService.updateMongoInstance(id, mongoModel);

        if(!result)  response = Response.serverError().build();

        else response = Response.ok().build();

        return response;

    }


}
