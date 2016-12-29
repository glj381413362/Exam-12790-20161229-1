package com;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.model.Film;

public class testJsonToBean {
public static void main(String[] args) {
	String string = "[{languageId:1,title:'aaaa',description:'描述'},{languageId:1,title:'aaaa',description:'描述'},{languageId:1,title:'aaaa',description:'描述'}]";
	Gson gson = new  Gson();
	List<Film> fromJson = gson.fromJson(string, new TypeToken<List<Film>>(){}.getType());
	System.out.println();
//	Film film = gson.fromJson(string, Film.class);
//	System.out.println(film.getFilmId()+film.getTitle()+"  "+film.getDescription());
}
}
