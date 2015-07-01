package com.lbi.mytestapplication.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lbi.mytestapplication.common.EntityMapper;
import com.lbi.mytestapplication.domain.entity.Route;
import com.lbi.mytestapplication.process.RouteManager;

@Path("/route")
public class RouteService {

	@Inject
	RouteManager routeMgr;
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<com.lbi.mytestapplication.rest.ressource.Route> getAllRoutes() {
        List<Route> entities =  routeMgr.getAllRoutes();
        List<com.lbi.mytestapplication.rest.ressource.Route> resources = 
        		new ArrayList<com.lbi.mytestapplication.rest.ressource.Route>();
        for(Route r : entities){
        	resources.add(EntityMapper.mapDomainEntityToRestResource(r));
        }
        return resources;
    }
    
    
    @Path("{id}")
    @GET
    public Response getRoute(@PathParam("id") int id,  @QueryParam("action") String action) {
    	Response.ResponseBuilder builder = null;
        Route route = routeMgr.getRouteById(id);
        if(action != null){
        	try {
				route = routeMgr.performAction(route, action);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				builder = Response.status(Response.Status.BAD_REQUEST).entity(route);
			}
        }
        com.lbi.mytestapplication.rest.ressource.Route routeRsc = 
        		EntityMapper.mapDomainEntityToRestResource(route);
        builder = Response.ok().entity(route);
        return builder.build();
    }
    

    /**
     * Creates a new endpoint from the values provided. Performs validation, and will return a JAX-RS response with either 200 ok,
     * or with a map of fields, and related errors.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRoute(com.lbi.mytestapplication.rest.ressource.Route route) {
        Response.ResponseBuilder builder = null;

        try {
        	Route r = EntityMapper.mapRestResourceToDomainEntity(route);
            routeMgr.createRoute(r);
            // Create an "ok" response
            builder = Response.ok().entity(route);
        } catch (Exception e) {
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<String, String>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
    }
}
