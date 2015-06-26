package com.lbi.mytestapplication.rest.ressource;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Connector {

	private long id;
	
	private EndPoint endpoint;
	private String name;
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
	 * @return the endpoint
	 */
	public EndPoint getEndpoint() {
		return endpoint;
	}
	/**
	 * @param endpoint the endpoint to set
	 */
	public void setEndpoint(EndPoint endpoint) {
		this.endpoint = endpoint;
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

}
