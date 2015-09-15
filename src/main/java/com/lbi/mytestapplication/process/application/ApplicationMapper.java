package com.lbi.mytestapplication.process.application;

import java.util.ArrayList;
import java.util.List;

import com.lbi.mytestapplication.domain.entity.Application;
import com.lbi.mytestapplication.domain.entity.Queue;

public class ApplicationMapper {

	public static ApplicationDTO mapDomainEntityToDto(Application app) {
		ApplicationDTO appDto = new ApplicationDTO(app.getName());
		appDto.setId(app.getId());
		appDto.setUrl(app.getUrl());
		List<QueueDTO> queues = new ArrayList<QueueDTO>();
		for(Queue q : app.getQueues()){
			QueueDTO qDto = mapDomainEntityToDto(q);
			queues.add(qDto);
		}
		appDto.setQueues(queues);
		return appDto;
	}

	private static QueueDTO mapDomainEntityToDto(Queue q) {
		QueueDTO qDto = new QueueDTO(q.getName());
		qDto.setFqName(q.getFqName());
		qDto.setId(q.getId());
		qDto.setTopic(q.isTopic());
		return qDto;
	}
	
	public static Application mapDtoToDomain(ApplicationDTO dto){
		Application app = new Application();
		app.setName(dto.getName());
		app.setUrl(dto.getUrl());
		app.setUseAmq(dto.isUseAmq());
		List<Queue> queues = new ArrayList<Queue>();
		for(QueueDTO qDto : dto.getQueues()){
			Queue q = mapDtoToDomain(qDto);
			q.setApplication(app);
			queues.add(q);
		}
		app.setQueues(queues);
		return app;
	}

	
	public static Queue mapDtoToDomain(QueueDTO dto){
		Queue q = new Queue();
		q.setName( dto.getName());
		q.setFqName(dto.getFqName());
		q.setTopic(dto.isTopic());
		return q;
	}

	
}
