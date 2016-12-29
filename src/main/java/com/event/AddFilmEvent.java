package com.event;

import org.springframework.context.ApplicationEvent;

public class AddFilmEvent extends ApplicationEvent{
	private String name;
	public AddFilmEvent(String source) {
		super(source);
		this.name = source;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
