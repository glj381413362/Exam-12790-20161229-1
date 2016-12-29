package com;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.model.Film;
import com.service.impl.FilmServiceImpl;

public class FilmServiceTest {
	private ConfigurableApplicationContext application;
	private FilmServiceImpl filmService;

	@Before
	public void setUp() throws Exception {
		application = new ClassPathXmlApplicationContext("classpath:config_spring/ApplicationContext.xml");
		filmService = (FilmServiceImpl) application.getBean("filmServiceImpl");
	}

	@Test
	public void testInsertFullFilmService() {
		Film film = (Film) application.getBean("film");
		film.setDescription("test数据");
//		film.setFilmId((short) 1114);
		film.setLanguageId(1);
//		film.setLastUpdate(new Date());
		film.setLength((short) 11);
		film.setOriginalLanguageId(1);
		film.setRating("2");
		film.setReleaseYear("1990");
		film.setRentalDuration(1);
		film.setRentalRate(new BigDecimal(Double.toString(0.48)));
		film.setReplacementCost(new BigDecimal(Double.toString(0.48)));
		film.setSpecialFeatures("Trailers"); 
		film.setTitle("成功");
		filmService.insertFullFilmService(film);

	}

	@Test
	public void testInsertSelectiveService() {
		Film film = (Film) application.getBean("film");
		film.setDescription("test数据");
//		film.setFilmId((short) 1111);
		film.setLanguageId(1);
//		film.setLastUpdate(new Date());
		film.setLength((short) 11);
		film.setOriginalLanguageId(1);
		film.setRating("2");
//		film.setReleaseYear(new Date(2000, 0, 0));
		film.setRentalDuration(1);
		film.setRentalRate(new BigDecimal(Double.toString(0.48)));
		film.setReplacementCost(new BigDecimal(Double.toString(0.48)));
		/* film.setSpecialFeatures("a"); */
		film.setTitle("成功");
		filmService.insertFullFilmService(film);
	}

	@Test
	public void testSelectByPrimaryKeyService() {
		filmService.selectByPrimaryKeyService((short) 3);
	}
	
	@Test
	public void selectSingleServiceTest() {
		Film film = (Film) application.getBean("film");
		film.setLanguageId(1);
		film.setTitle("d");
		Film film2 = filmService.selectSingleByWhereService(film);
		System.out.println(film2.getFilmId());
	}
	@Test
	public void selectListServiceTest() {
		Film film = (Film) application.getBean("film");
		film.setTitle("成功");
		film.setLimitStart(3);
		film.setLimitEnd(4);
		List<Film> film2 = filmService.selectListByWhereService(film);
		Iterator<Film> iterator = film2.iterator();
		while (iterator.hasNext()) {
			Film film3 = (Film) iterator.next();
			System.out.println(film3.getFilmId());
		}
		System.out.println(film2.size());
	}

}
