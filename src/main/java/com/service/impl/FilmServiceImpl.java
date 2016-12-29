package com.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.Errors;

import com.dao.FilmDao;
import com.event.AfterInsertFilmEvent;
import com.event.BeforeInsertFilmEvent;
import com.model.Film;
import com.service.IFilmService;
import com.validator.FilmValidator;

public class FilmServiceImpl implements IFilmService {
	private FilmDao filmDao;
	private FilmValidator filmValidator;
	private Object lock = new Object();
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	public FilmServiceImpl(FilmDao filmDao) {
		System.out.println("构造注入");
		this.filmDao = filmDao;
	}

	@Override
	public int insertFullFilmService(Film film) {
		return filmDao.insertFullFilm(film);
	}

	@Override
	public int insertSelectiveService(Film film) {
		applicationEventPublisher.publishEvent(new BeforeInsertFilmEvent("BeforeInsertFilmEvent"));
		int res = 0;
		synchronized (lock) {
			if(filmDao.selectSingleByWhere(film)==null){
				res = filmDao.insertSelective(film);;
			}
		}
		if(res>0)applicationEventPublisher.publishEvent(new AfterInsertFilmEvent("AfterInsertFilmEvent"));
		return res;
	}
	@Override
	public Film updateFilmByPrimaryKey(Film film) {
		filmDao.updateFilmByPrimaryKey(film);
		return filmDao.selectSingleByWhere(film);
	}
	@Override
	public int insertBatchFilmService(List<Film> fList) {
		return filmDao.insertBatchFilm(fList);
	}
	@Override
	public Map<String, Boolean> batchInsertFilmService(List<Film> fList) {
		applicationEventPublisher.publishEvent(new BeforeInsertFilmEvent("BeforeInsertFilmEvent"));
		Map<String, Boolean> resultMap = new HashMap<String, Boolean>();
		for(Film film : fList){
			if (filmValidator.supports(film.getClass())) {
				Errors errors = new DirectFieldBindingResult(film.getTitle(), film.hashCode() + "");
				filmValidator.validate(film, errors);
				if(errors.hasErrors()){
					System.out.println("Film can't null");
				}else {
					int res = insertSelectiveService(film);
					boolean r = (res>0);
					if(r)applicationEventPublisher.publishEvent(new AfterInsertFilmEvent("AfterInsertFilmEvent"));
					resultMap.put(String.valueOf("Film "+film.getTitle()+" "+film.getLanguageId()), r);
				}
			}
		}
		return resultMap;
	}
	@Override
	public Film selectByPrimaryKeyService(Short filmId) {
		return filmDao.selectByPrimaryKey(filmId);
	}

	@Override
	public Film selectSingleByWhereService(Film film) {
		return filmDao.selectSingleByWhere(film);
	}

	@Override
	public List<Film> selectListByWhereService(Film film) {
		return filmDao.selectListByWhere(film);
	}
	
	@Override
	public int deleteByPrimaryKeyService(Short filmId) {
		return filmDao.deleteByPrimaryKey(filmId);
	}

	public void setFilmDao(FilmDao filmDao) {
		System.out.println("stter注入");
		this.filmDao = filmDao;
	}

	@Override
	public List<Film> findFilmByNameLike(String name) {
		Film film = new Film();
		film.setTitle(name);
		return filmDao.findFilmByNameLike(film);
	}

	public void setFilmValidator(FilmValidator filmValidator) {
		this.filmValidator = filmValidator;
	}

	public void setLock(Object lock) {
		this.lock = lock;
	}

	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	

	
	




}
