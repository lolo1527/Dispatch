package com.lbi.mytestapplication.process.endpoint;

public class EndPointDTO {

	private long id;
	private String application;
	private String url;
	private String produceQueue;
	private String consumeQueue;
	private int pqSize;
	private int cqSize;
	
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
	public String getApplication() {
		return application;
	}
	/**
	 * @param application the application to set
	 */
	public void setApplication(String application) {
		this.application = application;
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
	 * @return the produceQueue
	 */
	public String getProduceQueue() {
		return produceQueue;
	}
	/**
	 * @param produceQueue the produceQueue to set
	 */
	public void setProduceQueue(String produceQueue) {
		this.produceQueue = produceQueue;
	}
	/**
	 * @return the consumeQueue
	 */
	public String getConsumeQueue() {
		return consumeQueue;
	}
	/**
	 * @param consumeQueue the consumeQueue to set
	 */
	public void setConsumeQueue(String consumeQueue) {
		this.consumeQueue = consumeQueue;
	}
	/**
	 * @return the pqSize
	 */
	public int getPqSize() {
		return pqSize;
	}
	/**
	 * @param pqSize the pqSize to set
	 */
	public void setPqSize(int pqSize) {
		this.pqSize = pqSize;
	}
	/**
	 * @return the cqSize
	 */
	public int getCqSize() {
		return cqSize;
	}
	/**
	 * @param cqSize the cqSize to set
	 */
	public void setCqSize(int cqSize) {
		this.cqSize = cqSize;
	}

}
