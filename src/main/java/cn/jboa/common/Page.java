package cn.jboa.common;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int currentPageNum;//��ǰҳ
	private int pageSize=10;//ҳ���С
	private int totalRecords;//�ܼ�¼����
	private int startIndex;//��ѯ��ʼ����
	private int totalPageNum;//��ҳ��
	private int prePageNum;//��һҳ
	private int nextPageNum;//��һҳ
	private List<?> records;//�������
	
	public Page(int currentPageNum,int totalRecords) {
		super();
		this.currentPageNum = currentPageNum;	//��ǰҳ
		this.totalRecords = totalRecords;		//���м�¼
		//���㿪ʼ����
		startIndex = (currentPageNum-1)*pageSize;
		//������ҳ��
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