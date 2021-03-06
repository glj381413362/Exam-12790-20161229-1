package com.command.parser.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.MainApp;
import com.command.Command;
import com.command.parser.Parser;
import com.utils.ReadPropertiesUtils;

@Component
public class DeleteParser implements Parser{
	private static final List< String> list;
	static{
		list = new ArrayList<String>();
		Map<String, String> argsMap = ReadPropertiesUtils.loadToMap("src/main/java/argsCommand.properties");
		String deleteStr = argsMap.get("delete");//-like<start end>,-like,<start end>,-id
		String[] arr = null;
		if(deleteStr.contains(",")){
			arr = deleteStr.split(",");
			for(String str : arr){
				list.add(str);
			}
		}else {
			list.add(deleteStr);
		}
	}
	
	@Override
	public boolean isRightOption(Map<String,String> argsMap) {
		boolean res = false;
		if(argsMap.size()==0)
		{
			res =true;
		}else{
			Iterator<String> iterator = argsMap.keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
//				String value=argsMap.get(key);
				if(list.contains(key)){
					res =true;
				}
			}
		}
		return res;
	}
	@Override
	public Command parse(String cmdName) {
		//获得Command
		return (Command) MainApp.getaContext().getBean(cmdName);
	}
				
}
