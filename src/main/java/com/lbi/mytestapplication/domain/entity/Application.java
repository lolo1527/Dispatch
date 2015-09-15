package com.lbi.mytestapplication.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="DIS_APPLICATION")
public class Application {

	@Id@GeneratedValue
	private long id;
	private String name;
	private String url;
	@OneToMany(mappedBy="application")
	private List<Queue> queues = new ArrayList<Queue>();
	boolean useAmq = false;
	
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
	public List<Queue> getQueues() {
		return queues;
	}
	/**
	 * @param queues the queues to set
	 */
	public void setQueues(List<Queue> queues) {
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
