package cn.jboa.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;

import cn.jboa.entity.ClaimVoucherStatistics;
import cn.jboa.service.ClaimVoucherStatisticsService;

@Service("compMonStatisticsAction")
@Scope("prototype")
public class CompMonStatisticsAction extends BaseAction{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="claimVoucherStatisticsService")
	private ClaimVoucherStatisticsService claimVoucherStatisticsService;
	private int currMonth;
	private List<ClaimVoucherStatistics> compMonthList;
	private List<ClaimVoucherStatistics> compMonthDetail;
	private int year;
	private int startMonth;
	private int endMonth;
	private double totalCount;
	
	
	public double getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
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
	public int getCurrMonth() {
		return currMonth;
	}
	public void setCurrMonth(int currMonth) {
		this.currMonth = currMonth;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getList() throws Exception{
		System.out.println("getList=======================================getList");
		DetachedCriteria dc = DetachedCriteria.forClass(ClaimVoucherStatistics.class);
		if(year>0){
			dc.add(Restrictions.eq("year", year));
		}
		if(startMonth>0){
			dc.add(Restrictions.ge("month",startMonth));	
		}
		if(endMonth>0){
			dc.add(Restrictions.le("month",endMonth));	
		}
		dc.addOrder(Order.asc("year"));
		dc.addOrder(Order.asc("month"));
		compMonthList = new ArrayList<ClaimVoucherStatistics>();
		List<ClaimVoucherStatistics> temp = claimVoucherStatisticsService.findCompStatisticsByMonth(dc);		
		for(int i=0; i<temp.size(); i++){
			ClaimVoucherStatistics c = temp.get(i);
			for(int j=i+1; j<temp.size(); j++){
				ClaimVoucherStatistics s = temp.get(j);
				if(c.getYear()==s.getYear()&&c.getMonth()==s.getMonth()&&c.getTotalCount()>0){
					c.setTotalCount(c.getTotalCount()+s.getTotalCount());
					s.setTotalCount((double) 0);
				}
			}
			if(c.getTotalCount()>0){
				compMonthList.add(c);
			}
		}
		totalCount = 0;
		for(ClaimVoucherStatistics c:compMonthList){
			totalCount += c.getTotalCount();
		}
		ActionContext.getContext().put("compMonthList", compMonthList);
		System.out.println("getList=======================================getList");
		return "compMonthStatisticsList";
	}
	public String getDetail() throws Exception{
		System.out.println("getDetail=======================================getDetail");
		compMonthDetail = claimVoucherStatisticsService.findCompStatisticsDetailByMonth(year, currMonth);
		totalCount = 0;
		for(ClaimVoucherStatistics c:compMonthDetail){
			totalCount += c.getTotalCount();
		}
		ActionContext.getContext().put("compMonthDetail", compMonthDetail);
		System.out.println("getDetail=======================================getDetail");
		return "compMonthStatisticsDetail";
	}
}
