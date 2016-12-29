package com;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.command.CommandManager;
import com.service.IFilmService;
import com.utils.ReadPropertiesUtils;

public class MainApp {
	private static AbstractApplicationContext aContext = new ClassPathXmlApplicationContext(
			"config_spring/ApplicationContext.xml");
	private static boolean isExit = false;
	public static void main(String[] args) {
		aContext.start();
		while (!isExit) {
			showMenu();//输出菜单描述
			Scanner scanner = new Scanner(System.in);
			String in = scanner.nextLine();
			CommandManager commandManager = (CommandManager) aContext.getBean("commandManager");
			commandManager.handle(in);
		}
		aContext.registerShutdownHook();
		aContext.stop();
		System.out.println("退出成功");
	}
	
	public static void showMenu(){
		Map<String, String> showMenu = ReadPropertiesUtils.loadToMap("src/main/java/showMenu.properties");
		Iterator<String> iterator = showMenu.keySet().iterator();
		System.out.println("*************************************************");
		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			String value = showMenu.get(key);
			System.out.println(value);
		}
		System.out.println("*************************************************");
	}

	public static AbstractApplicationContext getaContext() {
		return aContext;
	}
	public static void setaContext(AbstractApplicationContext aContext) {
		MainApp.aContext = aContext;
	}
	public static boolean isExit() {
		return isExit;
	}
	public static void setExit(boolean isExit) {
		MainApp.isExit = isExit;
	}
}
