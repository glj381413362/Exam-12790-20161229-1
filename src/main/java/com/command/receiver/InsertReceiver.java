package com.command.receiver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.model.Film;
import com.service.IFilmService;
import com.utils.Utils;

public class InsertReceiver {

	public List<Film> getArgsCommandResult(IFilmService filmService, Map<String, String> argsMap) {
		List<Film> fList = new ArrayList<Film>();
		Iterator<String> iterator = argsMap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			key = parserKeyCommand(key); // 解析key为-like<n,n>，-like<field,strArg>，-like<field,strArg>
			String value = argsMap.get(key);
			Gson gson = new Gson();
			switch (key) {
			case "-single":
				Film film = gson.fromJson(value, Film.class);
				Short filmId = film.getFilmId();
				if (filmId == null) {
					filmService.insertSelectiveService(film);
					fList.add(film);
				} else if (filmService.selectByPrimaryKeyService(filmId) != null) {
					System.out.println("该条记录已存在！");
				} else {
					filmService.insertSelectiveService(film);
					fList.add(film);
				}
				break;
			case "-batch":
				List<Film> films = gson.fromJson(value, new TypeToken<List<Film>>() {
				}.getType());
				// Map<String, Boolean> resMap =
				// filmService.insertBatchFilmService(films);
				// printInsertResult(resMap);
				int d = filmService.insertBatchFilmService(films);
				System.out.println("ddd" + d);
//				Map<String, String> listMap = getListMapToInsert(films);
				
				break;
			default:
				break;
			}
		}
		return fList;
	}

	/**
	 * 将要插入的对象放入一个List<Map<字段名,对应值>>
	 * 
	 */

	public Map<String, String> getListMapToInsert(List<Film> fList) {
		Map insertMap = new HashMap(); 
		List<Map<String, String>> lineList = new ArrayList<Map<String, String>>();		
		Iterator<Film> iterator = fList.iterator();
		while (iterator.hasNext()) {
			Map<String, String> map = new HashMap<String, String>();
			Film film = (Film) iterator.next();
			if (film.getTitle() != null) {
				map.put("title", film.getTitle());
			}
			if (film.getLanguageId() != null) {
				map.put("languageId", film.getLanguageId().toString());
			}
			if (film.getDescription() != null) {
				map.put("description", film.getDescription());
			}
			if (film.getFilmId() != null) {
				map.put("filmId", film.getFilmId().toString());
			}
			lineList.add(map);
		}
		String lineColumn = "";  //拼接的SQL,作为insert语句的一部分
		
		Map<String,String> lineMap = lineList.get(0); 
		for (String key : lineMap.keySet()) { 
		  lineColumn +=key+","; 
		} 
		lineColumn +="LINE_ID";
		
		insertMap.put("lineColumn",lineColumn);    
		insertMap.put("lineList", lineList);
		
		
		return insertMap;
	}

	public void printInsertResult(Map<String, Boolean> insertResultMap) {
		Iterator<String> it = insertResultMap.keySet().iterator();
		while (it.hasNext()) {
			String key;
			boolean value;
			key = it.next().toString();
			value = insertResultMap.get(key);
			System.out.println(key + "--" + value);
		}

	}

	public String parserKeyCommand(String key) {
		if (key.contains("<")) {// 命令为 <s,e>/args -like<s,e>/args 中的一种
			if (key.lastIndexOf("<") > 1) {// -like<s,e>/args 这一种
				String keys = key.substring(key.lastIndexOf("<"), key.lastIndexOf(","));// 获得-like<v1,v2>中的v1
				// 判断v1是否为数值，如果是数值则为分页查询
				if (Utils.isNumeric(keys)) {
					key = "-like<n,n>";
				} else {
					key = "-like<field,strArg>";
				}
			} else {// 命令为 <s,e>/args
					// 更改key 便于后面判断
				key = "<>";
			}
		}
		return key;
	}
}
