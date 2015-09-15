package com.lbi.mytestapplication.rest.ressource;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Queue {
	
	private long id;
	private String name;
	private String fqName;
	private int size;
	private boolean topic = false;

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
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
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

}
