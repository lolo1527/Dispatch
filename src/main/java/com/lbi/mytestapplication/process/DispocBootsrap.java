package com.lbi.mytestapplication.process;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.command.ActiveMQDestination;

import com.lbi.mytestapplication.process.application.ApplicationDTO;
import com.lbi.mytestapplication.process.endpoint.EndPointDTO;

@Singleton
@Startup
@TransactionManagement(TransactionManagementType.BEAN)
public class DispocBootsrap {

	static Logger logger = Logger.getLogger(DispocBootsrap.class.getName());
	static BrokerService broker = null;
	// use amq in default mode
	public static boolean useAmq = true;

	@Inject
	EndPointManager epMgr;

	@Inject
	ApplicationManager appMgr;

	@Inject
	RouteManager routeMgr;

	@Inject
	CamelManager camelMgr;
	
    @PostConstruct
    public void init() throws Exception {
    	// init broker (amq)
    	initBroker();
    	//init camel context
        logger.info(">> Create CamelContext");
        // Start Camel Context
        camelMgr.init();
        logger.info(">> CamelContext started");
        //init amq component
        if(useAmq){
        	camelMgr.initActiveMQ();
        }
        // populateDB & camel context
        initDB();
    }

    @PreDestroy
    public void stop() throws Exception {
    	camelMgr.stop();
    	broker.stop();
    }

    
	public void initDB(){
		// create EP
		EndPointDTO ep = new EndPointDTO();
		ep.setApplication("JCDStream");
		//ep.setUrl("seda://JCDStream");
		ep.setUrl("activemq://jcdstream");
		epMgr.createEndPoint(ep);
		// create EP2
		EndPointDTO ep2 = new EndPointDTO();
		ep2.setApplication("OpenLayer");
		//ep2.setUrl("seda://OpenLayer");
		ep2.setUrl("activemq://openlayer");
		epMgr.createEndPoint(ep2);
		//create application 
		ApplicationDTO app = new ApplicationDTO("Mosaic", useAmq);
		app.addQueue("fr.consume");
		app.addQueue("fr.produce");
		app.addQueue("fr.processed");
		appMgr.createApplication(app);
		// create route
		/*
		Route r = new Route();
		r.setDestination(EndPointMapper.mapProcessToDomainEntity(ep2));
		r.setSource(EndPointMapper.mapProcessToDomainEntity(ep));
		r.setStatus(Status.STOPPED);
		String id = r.getSource().getUrl()+ r.getDestination().getUrl();
		r.setRouteId(String.valueOf(id.hashCode()));
		routeMgr.createRoute(r);
		*/
	}

	public CamelManager getCamelManager(){
		return camelMgr;
	}
	

	public void initBroker() {
		logger.info(">> init AMQ Broker");
		broker = new BrokerService();
		// configure the broker
		try {
			broker.addConnector("tcp://localhost:61616");
			broker.start();
		} catch (Exception e) {
			logger.warning("Amq broker could not start => Exception : " + e.getMessage());
			logger.warning("Amq broker could not start => using seda endpoint instead of amq");
			useAmq = false;
		}
	}

    public static BrokerService getBrokerService(){
    	return broker;
    }
    

    public static void logBrokerService() throws Exception{
    	if(broker != null && broker.getBroker().getDestinations() != null){
        	ActiveMQDestination[] queues =  broker.getBroker().getDestinations();
	    	for(int i = 0; i < queues.length; i++){
	    		logger.info("broker queue : " + queues[i].getQualifiedName() + " - " + queues[i].getPhysicalName());
	    	}
    	}
    }

}
