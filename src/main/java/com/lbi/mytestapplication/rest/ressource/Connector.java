package com.lbi.mytestapplication.rest.ressource;

import javax.xml.bind.annotation.XmlRootElement;

import com.lbi.mytestapplication.common.Status;

@XmlRootElement
public class Connector {

	private long id;
	
	private String name;
	private String consumeQueue;
	private String produceQueue;
	private Status status;
	private String endPoint;
	
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
	 * @return the message
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param message the message to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the consumeQueue
	 */
	public String getConsumeQueue() {
		return consumeQueue;
	}
	/**
	 * @param consumeQueue the consumeQueue to set
	 */
	public void setConsumeQueue(String consumeQueue) {
		this.consumeQueue = consumeQueue;
	}
	/**
	 * @return the produceQueue
	 */
	public String getProduceQueue() {
		return produceQueue;
	}
	/**
	 * @param produceQueue the produceQueue to set
	 */
	public void setProduceQueue(String produceQueue) {
		this.produceQueue = produceQueue;
	}
	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	/**
	 * @return the endPoint
	 */
	public String getEndPoint() {
		return endPoint;
	}
	/**
	 * @param endPoint the endPoint to set
	 */
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

}
