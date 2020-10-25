package com.dev.metube.model;

public class Pagination {
	private Integer dataPerPage = 12;
	private Integer totalDataCount = 0;
	private Integer totalPages = 0;
	private Integer firstIndex = 0;
	private Integer currentIndex = 0;
	
	public Integer getDataPerPage() {
		return dataPerPage;
	}
	public void setDataPerPage(Integer dataPerPage) {
		this.dataPerPage = dataPerPage;
		setTotalDataCount(this.totalDataCount);
	}
	public Integer getTotalDataCount() {
		return totalDataCount;
	}
	public void setTotalDataCount(Integer totalDataCount) {
		this.totalDataCount = totalDataCount;
		this.totalPages = totalDataCount % dataPerPage == 0 ? totalDataCount / dataPerPage : totalDataCount / dataPerPage + 1;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	public Integer getFirstIndex() {
		return firstIndex;
	}
	public void setFirstIndex(Integer firstIndex) {
		this.firstIndex = firstIndex;
	}
	public Integer getCurrentIndex() {
		return currentIndex;
	}
	public void setCurrentIndex(Integer currentIndex) {
		this.currentIndex = currentIndex;
		this.firstIndex = (this.currentIndex - 1) * this.dataPerPage;
	}
}
