package cn.jboa.common;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int currentPageNum;//当前页
	private int pageSize=10;//页面大小
	private int totalRecords;//总记录条数
	private int startIndex;//查询开始索引
	private int totalPageNum;//总页数
	private int prePageNum;//上一页
	private int nextPageNum;//下一页
	private List<?> records;//最多五条
	
	public Page(int currentPageNum,int totalRecords) {
		super();
		this.currentPageNum = currentPageNum;	//当前页
		this.totalRecords = totalRecords;		//所有记录
		//计算开始索引
		startIndex = (currentPageNum-1)*pageSize;
		//计算总页数
		totalPageNum = totalRecords%pageSize==0 ? totalRecords/pageSize : totalRecords/pageSize+1;
	}
	
	public Page() {
		super();
	}

	public int getPrePageNum(){
		prePageNum = currentPageNum-1;
		if(prePageNum < 1){
			prePageNum = 1;
		}
		return prePageNum;
	}
	public int getNextPageNum(){
		nextPageNum = currentPageNum+1;
		if(nextPageNum > totalPageNum){
			nextPageNum = totalPageNum;
		}
		return nextPageNum;
	}

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public List<?> getRecords() {
		return records;
	}

	public void setRecords(List<?> records) {
		this.records = records;
	}

	public void setPrePageNum(int prePageNum) {
		this.prePageNum = prePageNum;
	}

	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}	
	
}