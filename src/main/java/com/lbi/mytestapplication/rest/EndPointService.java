package com.lbi.mytestapplication.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lbi.mytestapplication.common.EntityMapper;
import com.lbi.mytestapplication.process.EndPointManager;
import com.lbi.mytestapplication.process.endpoint.EndPointDTO;

@Path("/endpoint")
public class EndPointService {
	
	Logger logger = Logger.getLogger(EndPointService.class.getName());


	@Inject
	EndPointManager epMgr;
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<com.lbi.mytestapplication.rest.ressource.EndPoint> getAllEndPoints() {
        List<EndPointDTO> dtos =  epMgr.getAllEndPoints();
        List<com.lbi.mytestapplication.rest.ressource.EndPoint> resources = 
        		new ArrayList<com.lbi.mytestapplication.rest.ressource.EndPoint>();
        for(EndPointDTO epDto : dtos){
        	resources.add(EntityMapper.mapDtoToRestResource(epDto));
        }
        logger.info("endpoints.size = " + resources.size());
        return resources;
    }
    

    /**
     * Creates a new endpoint from the values provided. Performs validation, and will return a JAX-RS response with either 200 ok,
     * or with a map of fields, and related errors.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEndPoint(com.lbi.mytestapplication.rest.ressource.EndPoint endpoint) {
        Response.ResponseBuilder builder = null;
        logger.info("create endpoint : " + endpoint);
        try {
        	EndPointDTO ep = EntityMapper.mapRestResourceToDto(endpoint);
            epMgr.createEndPoint(ep);
            // Create an "ok" response
            builder = Response.ok().entity(endpoint);
        } catch (Exception e) {
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<String, String>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
    }
}
