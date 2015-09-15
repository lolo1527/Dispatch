package com.lbi.mytestapplication.common;


import java.util.ArrayList;
import java.util.List;

import com.lbi.mytestapplication.domain.entity.Connector;
import com.lbi.mytestapplication.domain.entity.EndPoint;
import com.lbi.mytestapplication.domain.entity.Post;
import com.lbi.mytestapplication.domain.entity.Route;
import com.lbi.mytestapplication.process.application.ApplicationDTO;
import com.lbi.mytestapplication.process.application.QueueDTO;
import com.lbi.mytestapplication.process.endpoint.EndPointDTO;
import com.lbi.mytestapplication.rest.ressource.Application;
import com.lbi.mytestapplication.rest.ressource.Queue;

public class EntityMapper {

	
    public static com.lbi.mytestapplication.rest.ressource.Route mapDomainEntityToRestResource(Route route){
    	com.lbi.mytestapplication.rest.ressource.Route routeRsc = new com.lbi.mytestapplication.rest.ressource.Route();
    	routeRsc.setId(route.getId());
    	routeRsc.setSource(mapDomainEntityToRestResource(route.getSource()));
    	routeRsc.setDestination(mapDomainEntityToRestResource(route.getDestination()));
    	routeRsc.setStatus(route.getStatus());
    	if(route.getRouteId() != null){
    		routeRsc.setRouteId(route.getRouteId());
    	} else{
    		String id = route.getSource().getUrl()+ route.getDestination().getUrl();
    		routeRsc.setRouteId(String.valueOf(id.hashCode()));
    	}
    	return routeRsc;
    }
    

    public static Route mapRestResourceToDomainEntity(com.lbi.mytestapplication.rest.ressource.Route routeRsc){
    	Route route = new Route();
    	route.setId(routeRsc.getId());
    	route.setSource(mapRestResourceToDomainEntity(routeRsc.getSource()));
    	route.setDestination(mapRestResourceToDomainEntity(routeRsc.getDestination()));
    	route.setStatus(routeRsc.getStatus());
    	if(routeRsc.getRouteId() != null){
    		route.setRouteId(routeRsc.getRouteId());
    	} else{
    		String id = routeRsc.getSource().getUrl()+ routeRsc.getDestination().getUrl();
    		route.setRouteId(String.valueOf(id.hashCode()));
    	}
    	return route;
    }

    public static com.lbi.mytestapplication.rest.ressource.EndPoint mapDomainEntityToRestResource(EndPoint ep){
    	com.lbi.mytestapplication.rest.ressource.EndPoint epr = new com.lbi.mytestapplication.rest.ressource.EndPoint();
    	epr.setId(ep.getId());
    	epr.setApplication(ep.getApplication());
    	epr.setUrl(ep.getUrl());
    	return epr;
    }
    

    public static EndPoint mapRestResourceToDomainEntity(com.lbi.mytestapplication.rest.ressource.EndPoint epr){
    	EndPoint ep = new EndPoint();
    	ep.setId(epr.getId());
    	ep.setApplication(epr.getApplication());
    	ep.setUrl(epr.getUrl());
    	return ep;
    }


	public static com.lbi.mytestapplication.rest.ressource.Post mapDomainEntityToRestResource(Post post) {
		com.lbi.mytestapplication.rest.ressource.Post postRsc = new com.lbi.mytestapplication.rest.ressource.Post();
		postRsc.setMessage(post.getMessage());
		postRsc.setQueue((post.getQueue()));
		postRsc.setId(post.getId());
		return postRsc;
	}


	public static Post mapRestResourceToDomainEntity(com.lbi.mytestapplication.rest.ressource.Post postRsc) {
		Post post = new Post();
		post.setQueue(postRsc.getQueue());
		post.setMessage(postRsc.getMessage());
		post.setId(postRsc.getId());
		return post;
	}


	public static Connector mapRestResourceToDomainEntity(com.lbi.mytestapplication.rest.ressource.Connector connector) {
		Connector connect = new Connector();
		connect.setName(connector.getName());
		connect.setId(connector.getId());
		connect.setConsumeQueue(connector.getConsumeQueue());
		connect.setProduceQueue(connector.getProduceQueue());
		connect.setStatus(Status.STARTED);
		connect.setEndPoint(connector.getEndPoint());
		return connect;
	}


	public static com.lbi.mytestapplication.rest.ressource.Connector mapDomainEntityToRestResource(Connector c) {
		com.lbi.mytestapplication.rest.ressource.Connector connectRsc = new com.lbi.mytestapplication.rest.ressource.Connector();
		connectRsc.setName(c.getName());
		connectRsc.setId(c.getId());
		connectRsc.setConsumeQueue(c.getConsumeQueue());
		connectRsc.setProduceQueue(c.getProduceQueue());
		connectRsc.setStatus(c.getStatus());
		connectRsc.setEndPoint(c.getEndPoint());
		return connectRsc;
	}


	public static com.lbi.mytestapplication.rest.ressource.EndPoint mapDtoToRestResource(EndPointDTO epDto) {
		com.lbi.mytestapplication.rest.ressource.EndPoint ep = new com.lbi.mytestapplication.rest.ressource.EndPoint();
		ep.setId(epDto.getId());
		ep.setApplication(epDto.getApplication());
		ep.setUrl(epDto.getUrl());
		ep.setConsumeQueue(epDto.getConsumeQueue());
		ep.setProduceQueue(epDto.getProduceQueue());
		ep.setCqSize(epDto.getCqSize());
		ep.setPqSize(epDto.getPqSize());
	    return ep;
	}


	public static EndPointDTO mapRestResourceToDto(com.lbi.mytestapplication.rest.ressource.EndPoint endpoint) {
		EndPointDTO ep = new EndPointDTO();
		ep.setId(endpoint.getId());
		ep.setApplication(endpoint.getApplication());
		ep.setUrl(endpoint.getUrl());
		ep.setConsumeQueue(endpoint.getConsumeQueue());
		ep.setProduceQueue(endpoint.getProduceQueue());
		//ep.setCqSize(epDto.getCqSize());
		//ep.setPqSize(epDto.getPqSize());
	    return ep;
	}


	public static Application mapDtoToRestResource(ApplicationDTO appDto) {
		Application app = new Application();
		app.setId(appDto.getId());
		app.setName(appDto.getName());
		app.setUrl(appDto.getUrl());
		List<Queue> queues = new ArrayList<Queue>();
		for(QueueDTO qDto : appDto.getQueues()){
			Queue q = mapDtoToRestResource(qDto);
			queues.add(q);
		}
		app.setQueues(queues);
		return app;
	}




	private static Queue mapDtoToRestResource(QueueDTO qDto) {
		Queue q = new Queue();
		q.setId(qDto.getId());
		q.setName(qDto.getName());
		q.setFqName(qDto.getFqName());
		q.setSize(qDto.getSize());
		q.setTopic(qDto.isTopic());
		return q;
	}


	public static ApplicationDTO mapRestResourceToDto(Application app, boolean useAmq) {
		ApplicationDTO appDto = new ApplicationDTO(app.getName(), useAmq);
		for(Queue queue : app.getQueues()){
			appDto.addQueue(queue.getName());
		}
		return appDto;
	}

}
