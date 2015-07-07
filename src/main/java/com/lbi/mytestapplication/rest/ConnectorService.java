package com.lbi.mytestapplication.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lbi.mytestapplication.common.EntityMapper;
import com.lbi.mytestapplication.domain.entity.Connector;
import com.lbi.mytestapplication.domain.entity.Post;
import com.lbi.mytestapplication.process.ConnectorManager;

@Path("/connector")
public class ConnectorService {
	
	Logger logger = Logger.getLogger(ConnectorService.class.getName());

	@Inject
	ConnectorManager connectorMgr;
	//ConnectorManager connectorMgr = new ConnectorManager();
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<com.lbi.mytestapplication.rest.ressource.Connector> getAllConnectors() {
        List<Connector> entities =  connectorMgr.getAllConnectors();
        List<com.lbi.mytestapplication.rest.ressource.Connector> resources = 
        		new ArrayList<com.lbi.mytestapplication.rest.ressource.Connector>();
        for(Connector c : entities){
        	resources.add(EntityMapper.mapDomainEntityToRestResource(c));
        }
        logger.info("connectors.size = " + resources.size());
        return resources;
    }
    

    /**
     * Creates a new endpoint from the values provided. Performs validation, and will return a JAX-RS response with either 200 ok,
     * or with a map of fields, and related errors.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createConnector(com.lbi.mytestapplication.rest.ressource.Connector connector) {
        Response.ResponseBuilder builder = null;
        try {
        	Connector conEntity = EntityMapper.mapRestResourceToDomainEntity(connector);
            logger.info("create connector : " + conEntity);
            connectorMgr.createConnector(conEntity);
            // Create an "ok" response
            builder = Response.ok().entity(connector);
        } catch (Exception e) {
            // Handle generic exceptions
        	logger.severe(e.getMessage());
        	logger.log(Level.SEVERE, e.getMessage(), e.getStackTrace());
            Map<String, String> responseObj = new HashMap<String, String>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
    }
    
    
    /**
     * Creates a new post from the values provided. Performs validation, and will return a JAX-RS response with either 200 ok,
     * or with a map of fields, and related errors.
     */
    @POST
    @Consumes("application/x-www-form-urlencoded")
    //@Produces(MediaType.APPLICATION_JSON)
    public Response postMessage(@FormParam("queue") String queue, @FormParam("message") String message) {
        Response.ResponseBuilder builder = null;
        logger.info("post message : " + message + "to queue : " + queue);
        try {
        	connectorMgr.postMessage(queue , message);
            // Create an "ok" response
            builder = Response.ok();
        } catch (Exception e) {
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<String, String>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
    }
}

