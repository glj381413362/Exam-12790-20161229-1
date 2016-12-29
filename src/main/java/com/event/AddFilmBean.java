package com.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.model.Film;
import com.service.IFilmService;

public class AddFilmBean implements ApplicationContextAware{
	private ApplicationContext applicationContext;
	@Autowired
	private IFilmService filmServiceImpl ;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	public void addFilm(String filmStr) {
		Film film = new Film();
		film.setLanguageId(1);
		film.setTitle(filmStr);
		if (filmServiceImpl.insertSelectiveService(film)>0) {
			AddFilmEvent addFilmEvent = new AddFilmEvent("添加Film成功！");
			applicationContext.publishEvent(addFilmEvent);
			return;
		}
	}
	public void setFilmService(IFilmService filmServiceImpl) {
		this.filmServiceImpl = filmServiceImpl;
	}
}
