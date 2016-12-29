package com.command;

import java.util.Map;

public class CommandInvoker {
	private Command command;
	public CommandInvoker(Command command){
		this.command=command;
	}
	public void action(Map<String,String> argsMap) {
		command.exe(argsMap);
	}
}
