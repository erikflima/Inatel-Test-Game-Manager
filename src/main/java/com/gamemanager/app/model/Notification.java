package com.gamemanager.app.model;

public class Notification {

    private String host;
    
    private String port;
    
    
    public  Notification() {
    }
    
    public Notification(String host, String port) {
		super();
		this.host = host;
		this.port = port;
	}

	public String getHost() {
        return this.host;
    }
    
    public String getPort() {
        return this.port;
    }
    
    public void setHost( String host) {
        this.host = host;
    }
    
    public void setPort( String port) {
        this.port = port;
    }

}