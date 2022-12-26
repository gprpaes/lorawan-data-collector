package br.ufrrj.labsd.mqtt;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("mqtt")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class MQTTResource {
    MQTTRepository mqttRepository = new MQTTRepository();
    MQTTService mqttService = new MQTTService(mqttRepository);


    @POST
    public Response createMQTTInstance(MQTTModel mqttModel) throws URISyntaxException {
        Integer created = mqttService.create(mqttModel);
        if(created == null) return  Response.serverError().build();

        return Response.created(new URI("http://localhost:8086/mqtt/"+created)).build();
    }

    @GET
    @Path("/{id}")
    public Response getMQTTInstanceById(@PathParam("id") int id){
        MQTTModel mqttModel = mqttService.get(id);
        if(mqttModel == null) return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok().entity(mqttModel).build();

    }

    @DELETE
    @Path("/{id}")
    public Response deleteMQTTInstanceById(@PathParam("id") int id){
        Integer rowCount = mqttService.delete(id);
        if(rowCount == null) return Response.serverError().build();

        Map<String, Integer> rowCountMap = new HashMap<>();
        rowCountMap.put("rowCount", rowCount);
        return Response.ok().entity(rowCountMap).build();


    }

    @GET
    public Response getAllMQTTInstances(){
        List<MQTTModel> mqttModels =  mqttService.getAll();
        if(mqttModels == null) return  Response.serverError().build();

        return  Response.ok(mqttModels).build();

    }

    @PUT
    @Path("/{id}")
    public Response updateMQTTInstance(@PathParam("id") Integer id, MQTTModel mqttModel){
        boolean result = mqttService.update(id, mqttModel);
        if(!result) return Response.serverError().build();

        return Response.ok().build();
    }
}
