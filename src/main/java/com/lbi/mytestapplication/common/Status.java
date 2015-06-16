/**
 * 
 */
package com.lbi.mytestapplication.common;

/**
 * @author loic
 *
 */
public enum Status {
	STOPPED("STOPPED"),
	STARTED("STARTED"),
	PAUSED("PAUSED");
	
	private final String value;

    private Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
