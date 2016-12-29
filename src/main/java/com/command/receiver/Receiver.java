package com.command.receiver;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.Film;
import com.service.IFilmService;

//show命令接受者
//@Component("receiver")  改用xml配置注入
public class Receiver {
//	@Autowired  改用xml配置注入
	private IFilmService filmService;

	public void showAction(Map<String, String> argsMap) {
		List<Film> fList = null;
		if (argsMap.size() == 0) {// 查询所有
			fList = filmService.selectListByWhereService(new Film());
		}else{
			fList = new ShowReceiver().getArgsCommandResult(filmService, argsMap);
		}
		if(fList!=null){
			printFilmList(fList);// 打印结果
		}
	}

	public void insertAction(Map<String, String> argsMap) {
		List<Film> fList = null;
	    fList = new InsertReceiver().getArgsCommandResult(filmService, argsMap);
		if(fList!=null){
			printFilmList(fList);// 打印结果
		}
	}
	
	public void deleteAction(Map<String, String> argsMap) {
		List<Film> fList = null;
	    fList = new DeleteReceiver().getArgsCommandResult(filmService, argsMap);
		if(fList!=null){
			System.out.print("成功删除：");
			printFilmList(fList);// 打印结果
		}
	}

	public void updateAction(Map<String, String> argsMap) {
		List<Film> fList = null;
	    fList = new UpdateReceiver().getArgsCommandResult(filmService, argsMap);
		if(fList!=null){
			printFilmList(fList);// 打印结果
		}
	}
	public void printFilmList(List<Film> fList) {
		if (fList.size() > 0) {
			Iterator<Film> iterator = fList.iterator();
			while (iterator.hasNext()) {
				Film film = (Film) iterator.next();
				if(film!=null)
				System.out.println("电影ID:" + film.getFilmId() + " 名称:" + film.getTitle()+" 描述:" + film.getDescription() + " 语言:" + film.getLanguageId());
			}
		} else {
			System.out.println("結果為空！");
		}
	}
	
	public void setFilmService(IFilmService filmService) {
		this.filmService = filmService;
	}
}
