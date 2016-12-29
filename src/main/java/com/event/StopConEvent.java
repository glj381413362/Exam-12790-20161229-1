package com.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;

public class StopConEvent implements ApplicationListener<ContextStoppedEvent>{

	@Override
	public void onApplicationEvent(ContextStoppedEvent arg0) {
		System.out.println("Application Stop!");
	}


}
