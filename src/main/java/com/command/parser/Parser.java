package com.command.parser;

import java.util.Map;

import com.command.Command;

public interface Parser {
	Command parse(String cmdName);
	boolean isRightOption(Map<String,String> argsMap);
}
