package com.ifisolution.model;

public enum MapperEnum {
	EMP_NAME("Name"),
	DATE_OF_BIRTH("Date"),
	GENDER("Gender"),
	TEAM("Team");	
	
	private String key;
	
	/**
     * Sole constructor
     */
	MapperEnum(String key) {
		this.key = key;
	}
	
	/**
     * Get key.
     * 
     * @return the key
     */
	public String get() {
        return key;
    }
		
}
