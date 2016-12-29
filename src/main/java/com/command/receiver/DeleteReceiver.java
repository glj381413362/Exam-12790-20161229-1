package com.command.receiver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.model.Film;
import com.service.IFilmService;
import com.utils.Utils;

public class DeleteReceiver {

	public List<Film> getArgsCommandResult(IFilmService filmService,Map<String, String> argsMap){
		List<Film> fList = new ArrayList<Film>();
		Iterator<String> iterator = argsMap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String value = argsMap.get(key);
			switch (key) {
			case "-id":
				//把要删除的对象查出，告诉用户
				Film film = filmService.selectByPrimaryKeyService(Short.parseShort(value));
				int i =filmService.deleteByPrimaryKeyService(Short.parseShort(value));
			    fList.add(film);
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
