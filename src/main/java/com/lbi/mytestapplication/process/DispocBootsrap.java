package com.lbi.mytestapplication.process;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import com.lbi.mytestapplication.common.Status;
import com.lbi.mytestapplication.domain.entity.EndPoint;
import com.lbi.mytestapplication.domain.entity.Route;

@Singleton
@Startup
@TransactionManagement(TransactionManagementType.BEAN)
public class DispocBootsrap {

	Logger logger = Logger.getLogger(DispocBootsrap.class.getName());

	@Inject
	EndPointManager epMgr;

	@Inject
	RouteManager routeMgr;

	@Inject
	CamelManager camelMgr;
	
    @PostConstruct
    public void init() throws Exception {
    	//init camel context
        logger.info(">> Create CamelContext");
        // Start Camel Context
        camelMgr.init();
        logger.info(">> CamelContext started");
        // populateDB & camel context
        initDB();
    }

    @PreDestroy
    public void stop() throws Exception {
    	camelMgr.stop();
    }

    
	public void initDB(){
		// create EP
		EndPoint ep = new EndPoint();
		ep.setApplication("JCDStream");
		ep.setUrl("seda://JCDStream");
		epMgr.createEndPoint(ep);
		// create EP2
		EndPoint ep2 = new EndPoint();
		ep2.setApplication("OpenLayer");
		ep2.setUrl("seda://OpenLayer");
		epMgr.createEndPoint(ep2);
		// create route
		Route r = new Route();
		r.setDestination(ep2);
		r.setSource(ep);
		r.setStatus(Status.STOPPED);
		routeMgr.createRoute(r);
	}

	public CamelManager getCamelManager(){
		return camelMgr;
	}
    
}
