package com.lbi.mytestapplication.common;

import com.lbi.mytestapplication.domain.entity.EndPoint;
import com.lbi.mytestapplication.domain.entity.Route;

public class EntityMapper {

	
    public static com.lbi.mytestapplication.rest.ressource.Route mapDomainEntityToRestResource(Route ep){
    	com.lbi.mytestapplication.rest.ressource.Route epr = new com.lbi.mytestapplication.rest.ressource.Route();
    	epr.setId(ep.getId());
    	epr.setSource(mapDomainEntityToRestResource(ep.getSource()));
    	epr.setDestination(mapDomainEntityToRestResource(ep.getDestination()));
    	epr.setStatus(ep.getStatus());
    	return epr;
    }
    

    public static Route mapRestResourceToDomainEntity(com.lbi.mytestapplication.rest.ressource.Route epr){
    	Route ep = new Route();
    	ep.setId(epr.getId());
    	ep.setSource(mapRestResourceToDomainEntity(epr.getSource()));
    	ep.setDestination(mapRestResourceToDomainEntity(epr.getDestination()));
    	ep.setStatus(epr.getStatus());
    	return ep;
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

}
