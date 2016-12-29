package com.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.model.Film;

public interface FilmDao {
	
	  
	    /**     
	     * @description 插入一个完整的FILM 所有字段都不允许为空
	     * @author 龚梁钧       
	     * @created 2016年12月22日 下午2:58:15     
	     * @param film
	     * @return  int   
	     */
	int insertFullFilm(Film film);
	
	int insertBatchFilm(List<Film> fList);
	  
	    /**     
	     * @description 插入Film 但是某些字段可以为空
	     * @author 龚梁钧       
	     * @created 2016年12月22日 下午2:58:53     
	     * @param film
	     * @return  int   
	     */
	int insertSelective(Film film);
	
	
	  
	    /**     
	     * @description 通过filmID查询
	     * @author 龚梁钧       
	     * @created 2016年12月22日 下午4:28:50     
	     * @param filmId
	     * @return  Film   
	     */
	Film selectByPrimaryKey(@Param("filmId") Short filmId);
	
	  
	    /**     
	     * @description 通过filmID删除
	     * @author 龚梁钧       
	     * @created 2016年12月22日 下午8:10:54     
	     * @param filmId
	     * @return  int   
	     */
	int deleteByPrimaryKey(@Param("filmId") Short filmId);
	
	int updateFilmByPrimaryKey(Film film);
	
	  
	    /**     
	     * @description 按一定条件(所有条件必须唯一确定一个Film否则会出错)查询Film
	     * @author 龚梁钧       
	     * @created 2016年12月23日 上午9:37:31     
	     * @param Entity
	     * @return   Film  
	     */
	Film selectSingleByWhere(Film Entity);
	  
	    /**     
	     * @description 查询满足一定条件的所有Film 可分页、排序
	     * @author 龚梁钧       
	     * @created 2016年12月23日 上午9:37:34     
	     * @param Entity
	     * @return List<Film>    
	     */
	List<Film> selectListByWhere(Film Entity);
	
	List<Film> findFilmByNameLike(Film Entity);
	
}
