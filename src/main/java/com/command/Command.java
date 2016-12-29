package com.command;

import java.util.Map;

public interface Command {
	//执行
	void exe(Map<String,String> argsMap);
}
