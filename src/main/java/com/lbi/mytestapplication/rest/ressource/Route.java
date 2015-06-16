package com.lbi.mytestapplication.rest.ressource;

import javax.xml.bind.annotation.XmlRootElement;

import com.lbi.mytestapplication.common.Status;

@XmlRootElement
public class Route {
	
	private long id;
	private EndPoint source;
	private EndPoint destination;
	private Status status;
	
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
	 * @return the source
	 */
	public EndPoint getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(EndPoint source) {
		this.source = source;
	}
	/**
	 * @return the destination
	 */
	public EndPoint getDestination() {
		return destination;
	}
	/**
	 * @param destination the destination to set
	 */
	public void setDestination(EndPoint destination) {
		this.destination = destination;
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


}
