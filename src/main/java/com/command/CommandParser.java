package com.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.MainApp;
import com.command.parser.Parser;
import com.utils.ReadPropertiesUtils;
import com.utils.Utils;

@Component
public class CommandParser {
	public void parse(String command) {
		String[] args = command.split("\\s+");
		if (args[0].length()<1) {
			throw new IllegalArgumentException("请输入命令符！");
		}else{
			exeCommand(args);//继续验证参数命令
		}
	}
	
	public void exeCommand(String[] args) {
		Map<String, String> cmdMap = ReadPropertiesUtils.loadToMap("src/main/java/command.properties");
		if(cmdMap.containsKey(args[0])){
			//判断命令是否正确
			Parser parserType = matchCommandType(args[0]+"Parser");//返回解析器类型
			//拆分参数为key/value  -like/args -id/args <s,e>/args  后面实现 -like<s,e>/args  命令
			Map<String,String> argsMap = Utils.stringToMap(args);
			//判断参数指令是否正确 首先判断参数个数是否为奇数 然后判断参数命令是否正确
			if(args.length%2==1&&parserType.isRightOption(argsMap)){
				Command command1 = parserType.parse(args[0]+"Command");//返回命令类型
				new CommandInvoker(command1).action(argsMap);//这里为何不只传入 argsMap 的value值呢，因为后面的操作可能还要
			}else {
				//提示参数指令不正确
				System.out.println("参数不足，或不正确");
			}
		}
	}
	public boolean paramsCountIsRight(String[] args) {
		boolean res = false;
		if (args[0].equals("show")||args[0].equals("update")) {
			if(args.length%2==1){
				res=true;
			}
		}else {
			
		}
		
		return res;
	}
	public Parser matchCommandType(String cmd){
		Parser result = (Parser) MainApp.getaContext().getBean(cmd);
		return result;
	}
	
}
