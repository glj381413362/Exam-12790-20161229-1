package com.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.io.Serializable;

public class Film implements Serializable  {

	private static final long serialVersionUID = 1L;
	private Short filmId;
	private String title;
	private String description;
	private String releaseYear;
	private Integer languageId;
	private Integer originalLanguageId;
	private Integer rentalDuration;
	private BigDecimal rentalRate;
	private Short length;
	private BigDecimal replacementCost;
	private String rating;
	private String specialFeatures;
	private Date lastUpdate;
	
	private Integer limitStart;
	private Integer limitEnd;
	private String strSqlWhere;
	private String strSqlOrderBy;
	/**
	 * 
	 * @return 
	 */
	public Short getFilmId() {
		return filmId;
	}

	/**
	 * 
	 * @param fbMonth 
	 */
	public void setFilmId(Short filmId) {
		this.filmId = filmId;
	}

	/**
	 * 
	 * @return 
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @param fbMonth 
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 
	 * @return 
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param fbMonth 
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return 
	 */
	public String  getReleaseYear() {
		return releaseYear;
	}

	/**
	 * 
	 * @param fbMonth 
	 */
	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}

	/**
	 * 
	 * @return 
	 */
	public Integer getLanguageId() {
		return languageId;
	}

	/**
	 * 
	 * @param fbMonth 
	 */
	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	/**
	 * 
	 * @return 
	 */
	public Integer getOriginalLanguageId() {
		return originalLanguageId;
	}

	/**
	 * 
	 * @param fbMonth 
	 */
	public void setOriginalLanguageId(Integer originalLanguageId) {
		this.originalLanguageId = originalLanguageId;
	}

	/**
	 * 
	 * @return 
	 */
	public Integer getRentalDuration() {
		return rentalDuration;
	}

	/**
	 * 
	 * @param fbMonth 
	 */
	public void setRentalDuration(Integer rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	/**
	 * 
	 * @return 
	 */
	public BigDecimal getRentalRate() {
		return rentalRate;
	}

	/**
	 * 
	 * @param fbMonth 
	 */
	public void setRentalRate(BigDecimal rentalRate) {
		this.rentalRate = rentalRate;
	}

	/**
	 * 
	 * @return 
	 */
	public Short getLength() {
		return length;
	}

	/**
	 * 
	 * @param fbMonth 
	 */
	public void setLength(Short length) {
		this.length = length;
	}

	/**
	 * 
	 * @return 
	 */
	public BigDecimal getReplacementCost() {
		return replacementCost;
	}

	/**
	 * 
	 * @param fbMonth 
	 */
	public void setReplacementCost(BigDecimal replacementCost) {
		this.replacementCost = replacementCost;
	}

	/**
	 * 
	 * @return 
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * 
	 * @param fbMonth 
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	/**
	 * 
	 * @return 
	 */
	public String getSpecialFeatures() {
		return specialFeatures;
	}

	/**
	 * 
	 * @param fbMonth 
	 */
	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	/**
	 * 
	 * @return 
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * 
	 * @param fbMonth 
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/**
	 * 
	 * @return 分页查询起期
	 */
	public Integer getLimitStart() {
		return limitStart;
	}

	/**
	 * 
	 * @param fbMonth 分页查询起期
	 */
	public void setLimitStart(Integer limitStart) {
		this.limitStart = limitStart;
	}

	/**
	 * 
	 * @return 分页查询止期
	 */
	public Integer getLimitEnd() {
		return limitEnd;
	}

	/**
	 * 
	 * @param fbMonth 分页查询止期
	 */
	public void setLimitEnd(Integer limitEnd) {
		this.limitEnd = limitEnd;
	}

	/**
	 * 
	 * @return 自定义sqlWhere
	 */
	public String getStrSqlWhere() {
		return strSqlWhere;
	}

	/**
	 * 
	 * @param fbMonth 自定义sqlWhere
	 */
	public void setStrSqlWhere(String strSqlWhere) {
		this.strSqlWhere = strSqlWhere;
	}

	/**
	 * 
	 * @return 自定义order by
	 */
	public String getStrSqlOrderBy() {
		return strSqlOrderBy;
	}

	/**
	 * 
	 * @param fbMonth 自定义order by
	 */
	public void setStrSqlOrderBy(String strSqlOrderBy) {
		this.strSqlOrderBy = strSqlOrderBy;
	}
}