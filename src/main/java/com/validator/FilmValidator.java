package com.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.model.Film;

public class FilmValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz==Film.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Film film = (Film) target;
		if(film.getTitle()==null){
			errors.rejectValue("title", "title不能为空");
		}
	}

}
