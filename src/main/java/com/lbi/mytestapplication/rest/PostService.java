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
import com.lbi.mytestapplication.domain.entity.Post;
import com.lbi.mytestapplication.process.PostManager;
import com.lbi.mytestapplication.process.post.PostProducer;

@Path("/post")
public class PostService {
	
	Logger logger = Logger.getLogger(PostService.class.getName());


	@Inject
	PostManager postMgr;
	
	@Inject
	PostProducer producer;
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<com.lbi.mytestapplication.rest.ressource.Post> getAllPosts() {
        List<Post> entities =  postMgr.getAllPosts();
        List<com.lbi.mytestapplication.rest.ressource.Post> resources = 
        		new ArrayList<com.lbi.mytestapplication.rest.ressource.Post>();
        for(Post ep : entities){
        	resources.add(EntityMapper.mapDomainEntityToRestResource(ep));
        }
        logger.info("posts.size = " + resources.size());
        return resources;
    }
    

    /**
     * Creates a new post from the values provided. Performs validation, and will return a JAX-RS response with either 200 ok,
     * or with a map of fields, and related errors.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPost(com.lbi.mytestapplication.rest.ressource.Post post) {
        Response.ResponseBuilder builder = null;
        logger.info("create endpoint : " + post);
        try {
        	Post postEntity = EntityMapper.mapRestResourceToDomainEntity(post);
        	producer.postMessage(postEntity.getQueue(), postEntity.getMessage());
            // Create an "ok" response
            builder = Response.ok().entity(post);
        } catch (Exception e) {
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<String, String>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
    }
}
