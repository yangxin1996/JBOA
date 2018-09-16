package cn.jboa.action;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.jboa.common.Page;
import cn.jboa.entity.ClaimVoucher;
import cn.jboa.entity.ClaimVouyearStatistics;
import cn.jboa.service.ClaimVouyearStatisticsService;

@Controller("deptStatisticsAction")
@Scope("prototype")
public class DeptStatisticsAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource(name="claimVouyearStatisticsService")
	private ClaimVouyearStatisticsService claimVouyearStatisticsService;
	private int startYear;
	private int endYear;
	private int pageNo;
	private List<ClaimVoucher> deptDetailList;
	private int currYear;
	private int departmentId;
	
	public int getCurrYear() {
		return currYear;
	}
	public void setCurrYear(int currYear) {
		this.currYear = currYear;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public int getStartYear() {
		return startYear;
	}
	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}
	public int getEndYear() {
		return endYear;
	}
	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public double getDetailCount() {
		double detailCount = 0;
		for(ClaimVoucher c:deptDetailList){
			detailCount += c.getTotalAccount();
		}
		return detailCount;
	}
	
	public String findDeptYearStatisticsDetail() throws Exception{
		System.out.println("findDeptYearStatisticsList=======================================findDeptYearStatisticsList");
		deptDetailList = claimVouyearStatisticsService.findDeptStatisticsDetailByYear(currYear, departmentId);
		ActionContext.getContext().put("deptDetailList", deptDetailList);		
		System.out.println("findDeptYearStatisticsList=======================================findDeptYearStatisticsList");
		return "deptYearStatisticsDetail";
	}
	public String findDeptYearStatisticsList() throws Exception{
		System.out.println("findDeptYearStatisticsList=======================================findDeptYearStatisticsList");		
		DetachedCriteria dc = DetachedCriteria.forClass(ClaimVouyearStatistics.class);
		dc.setProjection(Projections.count("id"));
		Integer positionId = getSessionPositionId();
		if(positionId==2){
			Integer deptID = getSessionEmployee().getSysDepartment().getId();	
			dc.add(Restrictions.eq("dept.id", deptID));
		}		
		if(currYear>0){
			dc.add(Restrictions.eq("year", currYear));
		}
		if(startYear>0){
			dc.add(Restrictions.ge("year", startYear));
		}
		if(endYear>0){
			dc.add(Restrictions.le("year",endYear));	
		}
		if(departmentId>0&&positionId>2){
			dc.add(Restrictions.eq("dept.id", departmentId));
		}
		dc.addOrder(Order.asc("year"));
		Page page = claimVouyearStatisticsService.findDeptStatisticsByYear(dc, pageNo);
		ActionContext.getContext().put("page", page);
		System.out.println("findDeptYearStatisticsList=======================================findDeptYearStatisticsList");
		return "deptYearStatisticsList";
	}
}
