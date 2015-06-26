package com.lbi.mytestapplication.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="DIS_CONNECTOR")
public class Connector {

	@Id@GeneratedValue
	private long id;
	
	@OneToOne
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
	
	
	public String toString(){
		return "name:" + name + ", endpoint:" + endpoint;
	}

}
