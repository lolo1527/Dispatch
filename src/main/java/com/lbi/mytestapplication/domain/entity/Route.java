package com.lbi.mytestapplication.domain.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lbi.mytestapplication.common.Status;

@Entity
@Table(name="DIS_ROUTE")
public class Route {
	
	@Id@GeneratedValue
	private long id;
	
	@OneToOne
	private EndPoint source;
	
	@OneToOne
	private EndPoint destination;
	
	@Enumerated(EnumType.STRING)
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
