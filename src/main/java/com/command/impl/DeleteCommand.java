package com.command.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.command.Command;
import com.command.receiver.Receiver;
@Component
public class DeleteCommand implements Command{
	@Autowired
	private Receiver receiver;

	@Override
	public void exe(Map<String,String> argsMap) {
		receiver.deleteAction(argsMap);
	}
	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}
}
