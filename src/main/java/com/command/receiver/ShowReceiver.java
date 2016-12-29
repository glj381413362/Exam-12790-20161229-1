package com.command.receiver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.model.Film;
import com.service.IFilmService;
import com.utils.Utils;

public class ShowReceiver {

	public List<Film> getArgsCommandResult(IFilmService filmService,Map<String, String> argsMap){
		List<Film> fList = new ArrayList<Film>();
		Iterator<String> iterator = argsMap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			key = parserKeyCommand(key);  //解析key为-like<n,n>，-like<field,strArg>，-like<field,strArg>
			String value = argsMap.get(key);
			/*String[] strArr = value.split("\\s+");//Map<cmd1,args>把参数args命令的参数拆分为Map<cmd2,arg>
			Map<String, String> argMap = Utils.stringToMap(strArr);
			Iterator<String> it = argMap.keySet().iterator();
			String values ="";
			while (it.hasNext()) {//获得参数命令的参数的值 (Map<cmd2,arg>)中的arg
				String k = it.next().toString();
				values = argMap.get(k);
			}*/
			switch (key) {
			case "-like":                               //所有字段模糊查询 ，但是这里只写了针对title模糊查
				fList = filmService.findFilmByNameLike(value);
				break;
			case "-like<n,n>"://分页模糊查询
				Film film = new Film();
				film.setLimitStart(1);                                        //暂时模拟数据
				film.setLimitEnd(6);
				fList = filmService.selectListByWhereService(film);
				break;
			case "-like<field,strArg>"://针对某一个字段field模糊查询
				System.out.println("参数正确，但是功能为完善！");
				break;
			case "<>"://分页查询
				System.out.println("参数正确，但是功能为完善！");
				break;
			case "-id":
				Film film1 = filmService.selectByPrimaryKeyService(Short.parseShort(value));
				fList.add(film1);
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
