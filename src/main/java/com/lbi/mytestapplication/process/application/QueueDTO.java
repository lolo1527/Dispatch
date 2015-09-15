package com.lbi.mytestapplication.process.application;

public class QueueDTO {
	
	private long id;
	private String name;
	private boolean useAmq;
	private String fqName;
	private int size = 0;
	private boolean topic = false;
	
	public QueueDTO(String name) {
		this.name = name;
	}

	
	public QueueDTO(String name, boolean useAmq, String url) {
		this.name = name;
		this.fqName = url + "." + name;
		this.useAmq = useAmq;
	}
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
