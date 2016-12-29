package com.command.receiver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.model.Film;
import com.service.IFilmService;
import com.utils.Utils;

public class UpdateReceiver {

	public List<Film> getArgsCommandResult(IFilmService filmService,Map<String, String> argsMap){
		List<Film> fList = new ArrayList<Film>();
		Iterator<String> iterator = argsMap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			key = parserKeyCommand(key);  //解析key为-like<n,n>，-like<field,strArg>，-like<field,strArg>
			String value = argsMap.get(key);
			switch (key) {
			case "-id":
				Gson gson = new Gson();
				Film film = gson.fromJson(value, Film.class);
				Short filmId = film.getFilmId();
				if(filmId!=null){
					if(filmService.selectByPrimaryKeyService(filmId)==null){
						System.out.println("数据库无该条记录，请确认ID是否正确！");
					}else {
						filmService.updateFilmByPrimaryKey(film);
						fList.add(film);
					}
				}else {
					System.out.println("id不能为空！");
				}
				break;
			default:
				break;
			}
		}
		return fList;
	}
	public String parserKeyCommand(String key) {
		if(key.contains("<")){//命令为 <s,e>/args   -like<s,e>/args 中的一种
			if(key.lastIndexOf("<")>1){//-like<s,e>/args 这一种
				String keys = key.substring(key.lastIndexOf("<"),key.lastIndexOf(","));//获得-like<v1,v2>中的v1
				//判断v1是否为数值，如果是数值则为分页查询
				if (Utils.isNumeric(keys)) {
					key= "-like<n,n>";
				}else {
					key="-like<field,strArg>";
				}
			}else {//命令为 <s,e>/args
				//更改key 便于后面判断
				key = "<>";
			}
		}
		return key;
	}
}
