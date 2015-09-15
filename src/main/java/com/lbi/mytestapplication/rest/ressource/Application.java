package com.lbi.mytestapplication.rest.ressource;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Application {
	
	private long id;
	private String name;
	private String url;
	private List<Queue> queues;

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
	 * @return the application
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param application the application to set
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

	public String toString(){
		return "app:" + name + "/url:" + url;
	}
	
	/**
	 * @return the queues
	 */
	public List<Queue> getQueues() {
		return queues;
	}
	/**
	 * @param queues the queues to set
	 */
	public void setQueues(List<Queue> queues) {
		this.queues = queues;
	}
	
	public void addQueue(String name) {
		Queue queue = new Queue();
		queue.setName(name);
		queues.add(queue);
	}

}
