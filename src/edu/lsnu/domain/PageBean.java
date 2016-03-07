package edu.lsnu.domain;

import java.util.List;

public class PageBean {
	// 查询数据库得到
	private int recordCount;// 记录条数
	private List recordList;
	// 根据需求提供
	private int currentPage;// 当前页
	private int pageSize; // 每页记录条数
	// 计算得出
	private int pageCount; // 总页数
	private int beginPageIndex;// 开始页数页号
	private int endPageIndex; // 结束页数页号

	public PageBean(int currentPage, int pageSize, int recordCount, List recordList) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		this.recordList = recordList;
		//计算总页数
		pageCount = (recordCount - 1) / pageSize + 1;
		
		//共显示10页 : 前4页+当前页+后5页
		//不足10页显示全部页
		if(pageCount<=10){
			beginPageIndex = 1;
			endPageIndex = pageCount;
		}else{
			beginPageIndex = currentPage - 4;
			endPageIndex = currentPage + 5;
			//前面不足4页，则显示前10页
			if(beginPageIndex<=0){
				beginPageIndex = 1;
				endPageIndex = 10;
			}
			//后面不足5页，则显示后10页
			if(endPageIndex>pageCount){
				endPageIndex = pageCount;
				beginPageIndex = endPageIndex - 9;
			}
		}
	}

	// ---

	public List getRecordList() {
		return recordList;
	}

	public void setRecordList(List recordList) {
		this.recordList = recordList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getBeginPageIndex() {
		return beginPageIndex;
	}

	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
}
