package com.event;

import org.springframework.context.ApplicationListener;

public class AddFilmNotifier implements ApplicationListener<AddFilmEvent>{

	@Override
	public void onApplicationEvent(AddFilmEvent event) {
		System.out.println("aaaaaaaaaaaa"+((AddFilmEvent) event).getName());  
	}

}
