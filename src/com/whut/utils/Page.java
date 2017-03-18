package com.whut.utils;

import java.util.List;

public class Page<T> {
	/**
	 * 当前页数
	 */
	private int currentPage;
	/**
	 * 总页数
	 */
	private int totalPage;
	/**
	 * 分页查询结果
	 */
	private List<T> pageList;

	public Page() {
	}

	public Page(int currentPage, int totalPage, List<T> pageList) {
		this.currentPage = currentPage;
		this.totalPage = totalPage;
		this.pageList = pageList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getPageList() {
		return pageList;
	}

	public void setPageList(List<T> pageList) {
		this.pageList = pageList;
	}
}
