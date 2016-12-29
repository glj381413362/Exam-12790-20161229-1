package com.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.model.Film;

public interface IBaseService<T,P> {
	/**
	 * @description 插入一个完整的Film 所有字段都不允许为空
	 * @author 龚梁钧
	 * @created 2016年12月22日 下午4:27:57
	 * @param film
	 */
	public int insertFullFilmService(T entity);

	/**
	 * @description 插入Film 但是某些字段可以为空
	 * @author 龚梁钧
	 * @created 2016年12月22日 下午4:27:59
	 * @param film
	 */
	public int insertSelectiveService(T entity);
	
	
	public int insertBatchFilmService(List<Film> fList);
	
	public int deleteByPrimaryKeyService(@Param("filmId") Short filmId);

	
	Film updateFilmByPrimaryKey(Film film);
	
	/**
	 * @description 通过filmID查询
	 * @author 龚梁钧
	 * @created 2016年12月22日 下午4:33:55
	 * @param filmId
	 * @return
	 */
	public T selectByPrimaryKeyService(P entityKey);

	/**
	 * @description 按一定条件(所有条件必须唯一确定一个Film否则会出错)查询Film
	 * @author 龚梁钧
	 * @created 2016年12月23日 上午9:34:42
	 * @param film
	 * @return
	 */
	public T selectSingleByWhereService(T entity);

	/**
	 * @description 查询满足一定条件的所有Film 可分页、排序
	 * @author 龚梁钧
	 * @created 2016年12月23日 上午9:36:43
	 * @param film
	 * @return
	 */
	public List<T> selectListByWhereService(T entity);
}
