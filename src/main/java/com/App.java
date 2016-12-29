package com;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.model.Film;
import com.service.IFilmService;

/**
 * Hello world!
 *
 */
public class App {
	private static IFilmService filmService ;
	public static void main(String[] args) {
		AbstractApplicationContext aContext = new ClassPathXmlApplicationContext(
				"config_spring/ApplicationContext.xml");
		aContext.start();
		filmService = (IFilmService) aContext.getBean("filmServiceImpl");
		mainApp(aContext);
	}

	public static void mainApp(AbstractApplicationContext aContext) {
		String in = printString("请选择: 1.增加新电影  2.删除电影  3.修改电影 4.查询电影");
		String op ;
		switch (Integer.parseInt(in)) {
		case 1:
			String inFilm = printString("请输入标题:(title,languageId)");
			List<Film> fList = new ArrayList<Film>();
			while (inFilm!=null&&!inFilm.equals(" ")&&inFilm.length()>0) {
				if(checkInput(inFilm)){
					fList.add(stringToFilm(inFilm));
				}else {
					System.out.println("输入数据有误！");
				}
				Scanner scanner = new Scanner(System.in);
				inFilm = scanner.nextLine();
			}
			Map<String, Boolean> insertResultMap = filmService.batchInsertFilmService(fList);
			printInsertResult(insertResultMap);
//			addFilmBean.addFilm(inFilm);
			op = printString("是否继续？ 1.继续  2.退出");
			isContinue(aContext,op);
			break;
		case 2:
			break;
		case 3:

			break;
		case 4:
			String title = printString("请输入title：");
			List<Film> filmLike = filmService.findFilmByNameLike(title);
			printFilmList(filmLike);
			op = printString("是否继续？ 1.继续  2.退出");
			isContinue(aContext,op);
			break;

		default:
			break;
		}
	}

	public static void printInsertResult(Map<String, Boolean> insertResultMap){
		Iterator<String> it=insertResultMap.keySet().iterator();   
		while(it.hasNext()){   
		     String key;   
		     boolean value;   
		     key=it.next().toString();   
		     value=insertResultMap.get(key);   
		     System.out.println(key+"--"+value);   
		}   
		
	}
	
	public static void isContinue(AbstractApplicationContext aContext,String op) {
		switch (Integer.parseInt(op)) {
		case 1:
			mainApp(aContext);
			break;
		case 2:
			aContext.registerShutdownHook();
			aContext.stop();
			break;

		default:
			break;
		}
	}
	
	public static boolean checkInput(String str){
		boolean res = false;
		String[] filmFileds = str.split(",");
		if(filmFileds.length>1){
			//后面加入id 范围判断 
			//这里只判断了是否为数值
			res = isNumeric(filmFileds[1].trim());
		}
		return res;
		
	}
	
	public static Film stringToFilm(String str) {
		String[] filmFileds = str.split(",");
		Film film = new Film();
		film.setTitle(filmFileds[0].trim());
		film.setLanguageId(Integer.parseInt(filmFileds[1].trim()));
		return film;
	}
	
	public static void printFilmList(List<Film> fList) {
		if (fList.size() > 0) {
			Iterator<Film> iterator = fList.iterator();
			while (iterator.hasNext()) {
				Film film = (Film) iterator.next();
				System.out.println("电影ID:" + film.getFilmId() + "名称:" + film.getTitle());
			}
		} else {
			System.out.println("查詢結果為空！");
		}
	}

	/**
	 * @description 正则判断是否为正数
	 * @author 龚梁钧
	 * @created 2016年12月22日 下午4:43:25
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public static String printString(String string) {
		System.out.println(string);
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	public static IFilmService getFilmService() {
		return filmService;
	}

	public static void setFilmService(IFilmService filmService) {
		App.filmService = filmService;
	}

}
