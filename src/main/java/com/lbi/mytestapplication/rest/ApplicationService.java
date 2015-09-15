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

import com.lbi.mytestapplication.common.Constant;
import com.lbi.mytestapplication.common.EntityMapper;
import com.lbi.mytestapplication.process.ApplicationManager;
import com.lbi.mytestapplication.process.DispocBootsrap;
import com.lbi.mytestapplication.process.application.ApplicationDTO;

@Path("/application")
public class ApplicationService {
	
	Logger logger = Logger.getLogger(ApplicationService.class.getName());


	@Inject
	ApplicationManager appMgr;
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<com.lbi.mytestapplication.rest.ressource.Application> getAllApplications() {
        List<ApplicationDTO> dtos =  appMgr.getAllApplications();
        List<com.lbi.mytestapplication.rest.ressource.Application> resources = 
        		new ArrayList<com.lbi.mytestapplication.rest.ressource.Application>();
        for(ApplicationDTO epDto : dtos){
        	resources.add(EntityMapper.mapDtoToRestResource(epDto));
        }
        logger.info("endpoints.size = " + resources.size());
        return resources;
    }
    

    /**
     * Creates a new app from the values provided. Performs validation, and will return a JAX-RS response with either 200 ok,
     * or with a map of fields, and related errors.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createApplication(com.lbi.mytestapplication.rest.ressource.Application application) {
        Response.ResponseBuilder builder = null;
        logger.info("create endpoint : " + application);
        try {
    		// create 2 default queues (produce & consume)
        	application.addQueue(Constant.PRODUCE);
        	application.addQueue(Constant.CONSUME);
        	//map to DTO
        	ApplicationDTO appDto = EntityMapper.mapRestResourceToDto(application, DispocBootsrap.useAmq);
        	// create app
            appMgr.createApplication(appDto);
            // Create an "ok" response
            builder = Response.ok().entity(application);
        } catch (Exception e) {
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<String, String>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
    }
}
