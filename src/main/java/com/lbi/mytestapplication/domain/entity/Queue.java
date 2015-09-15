package com.lbi.mytestapplication.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="DIS_QUEUE")
public class Queue {

	@Id@GeneratedValue
	private long id;
	private String name;
	private String fqName;
	private boolean topic;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="APP_ID")
	private Application application;
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
	 * @return the fqName
	 */
	public String getFqName() {
		return fqName;
	}
	/**
	 * @param fqName the fqName to set
	 */
	public void setFqName(String fqName) {
		this.fqName = fqName;
	}
	/**
	 * @return the topic
	 */
	public boolean isTopic() {
		return topic;
	}
	/**
	 * @param topic the topic to set
	 */
	public void setTopic(boolean topic) {
		this.topic = topic;
	}
	/**
	 * @return the application
	 */
	public Application getApplication() {
		return application;
	}
	/**
	 * @param application the application to set
	 */
	public void setApplication(Application application) {
		this.application = application;
	}

}
