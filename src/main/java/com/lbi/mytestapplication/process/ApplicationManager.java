package com.lbi.mytestapplication.process;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.transaction.UserTransaction;

import org.apache.camel.Endpoint;
import org.apache.camel.component.jms.JmsQueueEndpoint;
import org.apache.camel.component.seda.SedaEndpoint;

import com.lbi.mytestapplication.domain.ApplicationDAO;
import com.lbi.mytestapplication.domain.entity.Application;
import com.lbi.mytestapplication.domain.entity.EndPoint;
import com.lbi.mytestapplication.process.application.ApplicationDTO;
import com.lbi.mytestapplication.process.application.ApplicationMapper;
import com.lbi.mytestapplication.process.application.QueueDTO;
import com.lbi.mytestapplication.process.endpoint.EndPointMapper;

public class ApplicationManager {
	
	Logger logger = Logger.getLogger(EndPointManager.class.getName());

	@Inject
    private UserTransaction utx;

	@Inject
	ApplicationDAO dao;
	
	@Inject
	CamelManager camelMgr;


	public List<ApplicationDTO> getAllApplications() {
		List<ApplicationDTO> dtos = new ArrayList<ApplicationDTO>();
		List<Application> apps = dao.getAllApplications();
		for(Application app : apps){
			ApplicationDTO appDto = ApplicationMapper.mapDomainEntityToDto(app);
			// add camelMgr info (queues size)
			for(QueueDTO q : appDto.getQueues()){
				Endpoint ep = camelMgr.getCamelEndpoint(q.getFqName());
		    	if(ep.getEndpointUri().startsWith("seda")){
		    		q.setSize(((SedaEndpoint)ep).getExchanges().size());
		    	}else if (ep.getEndpointUri().startsWith("activemq")){
		    		q.setSize(((JmsQueueEndpoint)ep).getExchanges().size());
		    	}
			}
			dtos.add(appDto);
		}
		return dtos;
	}

	public void createApplication(ApplicationDTO dto) {
		try {
			utx.begin();
			Application app = ApplicationMapper.mapDtoToDomain(dto);
			dao.createApplication(app);
			addCamelEndpoints(dto.getQueues());
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

	private List<Endpoint> addCamelEndpoints(List<QueueDTO> queues) throws Exception {
		List<Endpoint> endpoints = new ArrayList<Endpoint>();
		for(QueueDTO qDto : queues){
			if(qDto.getFqName().startsWith("seda")){
				endpoints.add(camelMgr.addSEDAEndpoint(qDto.getFqName()));
			} else if(qDto.getFqName().startsWith("activemq")){
				endpoints.add(camelMgr.addJMSEndpoint(qDto.getFqName()));
			}
		}
		return endpoints;
	}

}
