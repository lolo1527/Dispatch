package com.lbi.mytestapplication.process.application;

import java.util.ArrayList;
import java.util.List;

import com.lbi.mytestapplication.common.Constant;


public class ApplicationDTO {

	private long id;
	private String name;
	private String url;
	private List<QueueDTO> queues = new ArrayList<QueueDTO>();
	boolean useAmq = false;
	
	
	public ApplicationDTO(String name) {
		this.name = name;
	}

	public ApplicationDTO(String name, boolean useAmq) {
		this.name = name;
		this.useAmq = useAmq;
		if(useAmq){
			url = Constant.AMQ_URL + name.toLowerCase();
		} else{
			url = Constant.SEDA_URL + name.toLowerCase();
		}
	}
	
	
	public void addQueue(String queueName){
		QueueDTO queue = new QueueDTO(queueName, useAmq, url);
		queues.add(queue);
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the queues
	 */
	public List<QueueDTO> getQueues() {
		return queues;
	}
	/**
	 * @param queues the queues to set
	 */
	public void setQueues(List<QueueDTO> queues) {
		this.queues = queues;
	}

	/**
	 * @return the useAmq
	 */
	public boolean isUseAmq() {
		return useAmq;
	}

	/**
	 * @param useAmq the useAmq to set
	 */
	public void setUseAmq(boolean useAmq) {
		this.useAmq = useAmq;
	}

}
