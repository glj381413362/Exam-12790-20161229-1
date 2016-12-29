package com.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
@Component
public class EventListener implements ApplicationListener{

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof BeforeInsertFilmEvent) {
			System.out.println( "Before Insert Film Data.");
		}
		if (event instanceof AfterInsertFilmEvent) {
			System.out.println("After Insert Film Data.");
		}		
	}

}
