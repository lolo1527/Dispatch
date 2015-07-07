package com.lbi.mytestapplication.rest.ressource;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Post {

	private long id;
	
	private String queue;
	private String message;
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
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the queue
	 */
	public String getQueue() {
		return queue;
	}
	/**
	 * @param queue the queue to set
	 */
	public void setQueue(String queue) {
		this.queue = queue;
	}

}
