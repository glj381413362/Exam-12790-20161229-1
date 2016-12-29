package com.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandManager {
	@Autowired
	private CommandParser commandParser;

	public void handle(String command) {
		commandParser.parse(command);
	}
	public void setCommandParser(CommandParser commandParser) {
		this.commandParser = commandParser;
	}

}
