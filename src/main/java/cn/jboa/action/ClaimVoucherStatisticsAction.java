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
import cn.jboa.entity.ClaimVoucherStatistics;
import cn.jboa.service.ClaimVoucherStatisticsService;

@Controller("claimVoucherStatisticsAction")
@Scope("prototype")
public class ClaimVoucherStatisticsAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name="claimVoucherStatisticsService")
	private ClaimVoucherStatisticsService claimVoucherStatisticsService;
	
	private int pageNo;
	private int year;
	private int startMonth;
	private int endMonth;
	private int selectMonth;
	private int departmentId;
	private String fileName;
	
	private List<ClaimVoucher> statisticsDetailOfDeptMonth;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getSelectMonth() {
		return selectMonth;
	}

	public void setSelectMonth(int selectMonth) {
		this.selectMonth = selectMonth;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(int startMonth) {
		this.startMonth = startMonth;
	}

	public int getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(int endMonth) {
		this.endMonth = endMonth;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public double getDetailCount() {
		double detailCount = 0;
		for(ClaimVoucher c:statisticsDetailOfDeptMonth){
			detailCount += c.getTotalAccount();
		}
		return detailCount;
	}
	
	public String getDeptStatisticsByMonth() throws Exception{
		System.out.println("getDeptStatisticsByMonth=======================================getDeptStatisticsByMonth");
		DetachedCriteria dc = DetachedCriteria.forClass(ClaimVoucherStatistics.class);
		dc.setProjection(Projections.count("id"));
		Integer positionId = getSessionPositionId();
		if(positionId==2){
			Integer deptID = getSessionEmployee().getSysDepartment().getId();	
			dc.add(Restrictions.eq("department.id", deptID));
		}
		if(year>0){
			dc.add(Restrictions.eq("year", year));
		}
		if(startMonth>0){
			dc.add(Restrictions.ge("month",startMonth));	
		}
		if(endMonth>0){
			dc.add(Restrictions.lt("month",endMonth));	
		}
		dc.addOrder(Order.asc("year"));
		dc.addOrder(Order.asc("month"));
		Page page = claimVoucherStatisticsService.findDeptStatisticsByMonth(dc, pageNo);
		ActionContext.getContext().put("page", page);
		System.out.println("getDeptStatisticsByMonth=======================================getDeptStatisticsByMonth");
		return "deptMonthStatisticsList";
	}
	
	public String getDeptVoucherDetailByMonth() throws Exception{
		System.out.println("getDeptVoucherDetailByMonth=======================================getDeptVoucherDetailByMonth");
		statisticsDetailOfDeptMonth = claimVoucherStatisticsService.findDeptStatisticsDetailByMonth(year,selectMonth,departmentId);
		ActionContext.getContext().put("statisticsDetailOfDeptMonth", statisticsDetailOfDeptMonth);
		System.out.println("getDeptVoucherDetailByMonth=======================================getDeptVoucherDetailByMonth");
		return "deptMonthStatisticsDetail";
	}	
	
}
