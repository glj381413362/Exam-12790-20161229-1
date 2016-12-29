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
		if (args[0].length() < 1) {
			throw new IllegalArgumentException("请输入命令符！");
		} else {
			exeCommand(args);// 继续验证参数命令
		}
	}

	public void exeCommand(String[] args) {
		Map<String, String> cmdMap = ReadPropertiesUtils.loadToMap("src/main/java/command.properties");
		if (cmdMap.containsKey(args[0])) {
			if (args[0].equals("exit")) {
				MainApp.setExit(true);
				return;
			} else {
				// 判断命令是否正确
				Parser parserType = matchCommandType(args[0] + "Parser");// 返回解析器类型
				// 拆分参数为key/value -like/args -id/args <s,e>/args 后面实现
				// -like<s,e>/args 命令
				Map<String, String> argsMap = Utils.stringToMap(args);
				// 判断参数指令是否正确 首先判断参数个数是否为奇数 然后判断参数命令是否正确
				if (args.length % 2 == 1 && parserType.isRightOption(argsMap)) {
					Command command1 = parserType.parse(args[0] + "Command");// 返回命令类型
					new CommandInvoker(command1).action(argsMap);// 这里为何不只传入
																	// argsMap
																	// 的value值呢，因为后面的操作可能还要
				} else {
					System.out.println(args[0]);
					// 提示参数指令不正确
					System.out.println("参数不足，或不正确");
					if (args[0].equals("show")) {
						System.out.println("模糊查询   show -like XXX  只写了模糊查询title");
						System.out.println("更据id查询 show -id XX");
					}
					if (args[0].equals("delete")) {
						System.out.println("删除       delete -id XX");
					}
					if (args[0].equals("update")) {
						System.out.println("更据id修改 update -id {字段名1：值1，字段名2：值2}必需包含id 不然修改失败 并提示");
					}
					if (args[0].equals("insert")) {
						System.out.println("单个插入   insert -single {字段名1：值1，字段名2：值2}包含id 如果数据库无该条记录 则能操作成功，否则失败，并提示 ");
						System.out.println(
								"insert -batch  [{title:值1,languageId:2,description:值3},{title:值1,languageId:值2,description:值3},...]");
						System.out.println("字段必须位 title languageId description");
					}
				}
			}

		} else {
			System.out.println("命令错误");
			System.out.println("正确命令：show delete update insert exit");
			System.out.println("单个插入   insert -single {字段名1:值1,字段名2:值2}包含id 如果数据库无该条记录 则能操作成功，否则失败，并提示 ");
			System.out.println("批量插入   insert -batch  [{title:值1,languageId:2,description:值3},{title:值1,languageId:值2,description:值3},...]");
			System.out.println("字段必须位 title languageId description");
			System.out.println("模糊查询   show -like XXX  只写了模糊查询title");
			System.out.println("更据id查询 show -id XX");
			System.out.println("更据id修改 update -id {字段名1：值1，字段名2：值2}必需包含id 不然修改失败 并提示");
			System.out.println("删除       delete -id XX");
		}
	}

	public Parser matchCommandType(String cmd) {
		Parser result = (Parser) MainApp.getaContext().getBean(cmd);
		return result;
	}

}
