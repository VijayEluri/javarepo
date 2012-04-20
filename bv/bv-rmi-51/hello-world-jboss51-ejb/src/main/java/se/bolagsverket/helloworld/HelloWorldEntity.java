package se.bolagsverket.helloworld;

import java.io.Serializable;

public class HelloWorldEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2947462627817574274L;
	private String name;
	
	public HelloWorldEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public HelloWorldEntity(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
