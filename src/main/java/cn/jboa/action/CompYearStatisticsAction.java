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

import cn.jboa.entity.ClaimVouyearStatistics;
import cn.jboa.service.ClaimVouyearStatisticsService;


@Service("compYearStatisticsAction")
@Scope("prototype")
public class CompYearStatisticsAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="claimVouyearStatisticsService")
	private ClaimVouyearStatisticsService claimVouyearStatisticsService;
	private List<ClaimVouyearStatistics> compYearList;
	private List<ClaimVouyearStatistics> compYearDetail;
	
	private int startYear;
	private int endYear;
	private int currYear;
	private double totalCount;
	
	public int getCurrYear() {
		return currYear;
	}
	public void setCurrYear(int currYear) {
		this.currYear = currYear;
	}
	public double getTotalCount() {
		return totalCount;
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

	public String getList() throws Exception{
		System.out.println("getList=======================================getList");
		DetachedCriteria dc = DetachedCriteria.forClass(ClaimVouyearStatistics.class);
		if(startYear>0){
			dc.add(Restrictions.ge("year", startYear));
		}
		if(endYear>0){
			dc.add(Restrictions.le("year",endYear));	
		}
		dc.addOrder(Order.asc("year"));
		
		compYearList = new ArrayList<ClaimVouyearStatistics>();
		List<ClaimVouyearStatistics> temp = claimVouyearStatisticsService.findCompStatisticsByYear(dc);		
		for(int i=0; i<temp.size(); i++){
			ClaimVouyearStatistics c = temp.get(i);
			for(int j=i+1; j<temp.size(); j++){
				ClaimVouyearStatistics s = temp.get(j);
				if(c.getYear()==s.getYear()&&c.getTotalCount()>0){
					c.setTotalCount(c.getTotalCount()+s.getTotalCount());
					s.setTotalCount((double) 0);
				}
			}
			if(c.getTotalCount()>0){
				compYearList.add(c);
			}
		}
		totalCount = 0;
		for(ClaimVouyearStatistics c:compYearList){
			totalCount += c.getTotalCount();
		}	
		ActionContext.getContext().put("compYearList", compYearList);
		System.out.println("getList=======================================getList");
		return "compYearStatisticsList";
	}
	public String getDetail() throws Exception{
		System.out.println("getDetail=======================================getDetail");
		DetachedCriteria dc = DetachedCriteria.forClass(ClaimVouyearStatistics.class);
		if(currYear>0){
			dc.add(Restrictions.eq("year", currYear));
		}
		compYearDetail = claimVouyearStatisticsService.findCompStatisticsByYear(dc);		
		totalCount = 0;
		for(ClaimVouyearStatistics c:compYearDetail){
			totalCount += c.getTotalCount();
		}
		ActionContext.getContext().put("compYearDetail", compYearDetail);
		System.out.println("getDetail=======================================getDetail");
		return "compYearStatisticsDetail";
	}
}
