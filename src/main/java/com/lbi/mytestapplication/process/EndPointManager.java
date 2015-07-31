package com.lbi.mytestapplication.process;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.transaction.UserTransaction;

import org.apache.camel.Endpoint;
import org.apache.camel.component.jms.JmsEndpoint;
import org.apache.camel.component.seda.SedaEndpoint;

import com.lbi.mytestapplication.domain.EndPointDAO;
import com.lbi.mytestapplication.domain.entity.EndPoint;
import com.lbi.mytestapplication.process.endpoint.EndPointDTO;
import com.lbi.mytestapplication.process.endpoint.EndPointMapper;

public class EndPointManager {
	
	Logger logger = Logger.getLogger(EndPointManager.class.getName());

	@Inject
    private UserTransaction utx;

	@Inject
	EndPointDAO dao;
	
	@Inject
	CamelManager camelMgr;


	public void createEndPoint(EndPointDTO epDto){
		try {
			utx.begin();
			EndPoint ep = EndPointMapper.mapProcessToDomainEntity(epDto);
			dao.createEndPoint(ep);
			addCamelEndpoints(ep.getUrl());
			utx.commit();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(),e);
			try {
				utx.rollback();
			} catch (Exception e1) {
				logger.log(Level.SEVERE, e.getMessage(),e);
			}
		}
	}

	public List<Endpoint> addCamelEndpoints(String url) throws Exception{
		List<Endpoint> endpoints = null;
		if(url.startsWith("seda")){
			endpoints = camelMgr.addSEDAEndpoints(url);
		} else if(url.startsWith("activemq")){
			endpoints = camelMgr.addJMSEndpoints(url);
		}
		return endpoints;
	}
	
	
	public Collection<Endpoint> getCamelEndpoints(){
		Collection<Endpoint> eps = camelMgr.getCamelEndpoints();
		for (Endpoint ep : eps){
			if(ep instanceof SedaEndpoint) {
				SedaEndpoint seda = (SedaEndpoint) ep;
				logger.fine("Camel Endpoint : " + ep + "- message(s) in queue : " + seda.getExchanges().size());
			}
			if(ep instanceof JmsEndpoint) {
				JmsEndpoint seda = (JmsEndpoint) ep;
				logger.fine("Camel Endpoint : " + ep + "- message(s) in queue : " + seda.getMaxConcurrentConsumers());
			}
		}
		return eps;
	}

	public List<EndPointDTO> getAllEndPoints() {
		List<EndPointDTO> dtos = new ArrayList<EndPointDTO>();
		List<EndPoint> eps = dao.getAllEndPoints();
		for(EndPoint ep : eps){
			Endpoint epc = camelMgr.getCamelConsumeEndpoint(ep.getUrl());
			Endpoint epp = camelMgr.getCamelProduceEndpoint(ep.getUrl());
			EndPointDTO epDto = EndPointMapper.mapDomainEntityToRestResource(ep, epc, epp);
			dtos.add(epDto);
		}
		return dtos;
	}

}
