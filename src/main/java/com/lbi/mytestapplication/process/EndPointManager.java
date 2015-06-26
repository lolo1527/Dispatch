package com.lbi.mytestapplication.process;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.transaction.UserTransaction;

import org.apache.camel.Endpoint;
import org.apache.camel.component.seda.SedaEndpoint;

import com.lbi.mytestapplication.domain.EndPointDAO;
import com.lbi.mytestapplication.domain.entity.EndPoint;

public class EndPointManager {
	
	Logger logger = Logger.getLogger(EndPointManager.class.getName());

	@Inject
    private UserTransaction utx;

	@Inject
	EndPointDAO dao;
	
	@Inject
	CamelManager camelMgr;


	public void createEndPoint(EndPoint ep){
		try {
			utx.begin();
			dao.createEndPoint(ep);
			addCamelEndpoint(ep.getUrl());
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

	public Endpoint addCamelEndpoint(String url){
		return camelMgr.addSEDAEndpoint(url);
	}
	
	
	public Collection<Endpoint> getCamelEndpoints(){
		Collection<Endpoint> eps = camelMgr.getCamelEndpoints();
		for (Endpoint ep : eps){
			if(ep instanceof SedaEndpoint) {
				SedaEndpoint seda = (SedaEndpoint) ep;
				logger.info("Camel Endpoint : " + ep + "- message(s) in queue : " + seda.getExchanges().size());
			}
			//camelMgr.getCamelEndpoint(ep.getEndpointKey());
		}
		return eps;
	}

	public List<EndPoint> getAllEndPoints() {
		getCamelEndpoints();		
		return dao.getAllEndPoints();
	}

}
