package com.lbi.mytestapplication.process.endpoint;

import org.apache.camel.Endpoint;
import org.apache.camel.component.seda.SedaEndpoint;
import org.apache.camel.component.jms.JmsQueueEndpoint;


import com.lbi.mytestapplication.common.Constant;
import com.lbi.mytestapplication.domain.entity.EndPoint;

public class EndPointMapper {

    public static EndPointDTO mapDomainEntityToRestResource(EndPoint ep, Endpoint epc, Endpoint epp){
    	EndPointDTO processEp = new EndPointDTO();
    	processEp.setId(ep.getId());
    	processEp.setApplication(ep.getApplication());
    	processEp.setUrl(ep.getUrl());
    	processEp.setConsumeQueue(processEp.getUrl()+ "." + Constant.CONSUME);
    	processEp.setProduceQueue(processEp.getUrl()+ "." + Constant.PRODUCE);
    	if(epc.getEndpointUri().startsWith("seda")){
    		processEp.setCqSize(((SedaEndpoint)epc).getExchanges().size());
    	}else if (epc.getEndpointUri().startsWith("activemq")){
    		processEp.setCqSize(((JmsQueueEndpoint)epc).getExchanges().size());
    	}
    	if(epp.getEndpointUri().startsWith("seda")){
    		processEp.setPqSize(((SedaEndpoint)epp).getExchanges().size());
    	}else if (epp.getEndpointUri().startsWith("activemq")){
    		processEp.setPqSize(((JmsQueueEndpoint)epp).getExchanges().size());
    	}
    	return processEp;
    }
    

    public static EndPoint mapProcessToDomainEntity(EndPointDTO processEp){
    	EndPoint ep = new EndPoint();
    	ep.setId(processEp.getId());
    	ep.setApplication(processEp.getApplication());
    	ep.setUrl(processEp.getUrl());
    	return ep;
    }

}
