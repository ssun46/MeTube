package com.dev.metube.model;

import java.util.List;

public class ResultList {
	private Pagination pagination;
	private List<?> resultList;

	public ResultList() {
		pagination = new Pagination();
	}
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public void setTotalCount(Integer count) {
		this.pagination.setTotalDataCount(count);
	}
	
	public void setDataPerPage(Integer count) {
		this.pagination.setDataPerPage(count);
	}
	
	public void setCurrentIndex(Integer index) {
		this.pagination.setCurrentIndex(index);
	}
	
	public List<?> getResultList() {
		return resultList;
	}

	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}
}
