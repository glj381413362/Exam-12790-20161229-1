package com.event;

import org.springframework.context.ApplicationEvent;

@SuppressWarnings("serial")
public class BeforeInsertFilmEvent extends ApplicationEvent{
	public BeforeInsertFilmEvent(String str) {
		super(str);
	}

}
