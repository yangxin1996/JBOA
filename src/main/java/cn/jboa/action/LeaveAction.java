package cn.jboa.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.jboa.common.Page;
import cn.jboa.entity.Employee;
import cn.jboa.entity.Leave;
import cn.jboa.service.LeaveService;

@Controller("leaveAction")
@Scope("prototype")
public class LeaveAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Leave leave;
	private int pageNo;
	private int pageSize;
    private String startDate;
    private String endDate;	
	@SuppressWarnings("unused")
	private Map<String, String> leaveTypeMap;
	
	@Resource(name="leaveService")
	private LeaveService leaveService;
	
	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public Leave getLeave() {
		return leave;
	}

	public void setLeave(Leave leave) {
		this.leave = leave;
	}

	public Map<String, String> getLeaveTypeMap() throws Exception{
        return leaveService.getLeaveTypeMap();
    }
	
	public String checkLeave() throws Exception{
		System.out.println("checkLeave=======================================checkLeave");
		if(leave==null||leave.getId()==null){
			return NONE;
		}
		Leave temp = leaveService.findLeaveById(leave.getId());
		temp.setStatus(leave.getStatus());
		temp.setApproveOpinion(leave.getApproveOpinion());
		temp.setModifyTime(new Date());
		leaveService.saveLeave(temp);
		System.out.println("checkLeave=======================================checkLeave");
		return "chain";
	}
    
	public String getLeaveById() throws Exception{
		System.out.println("getLeaveById=======================================getLeaveById");
		if(leave==null||leave.getId()==null){
			return NONE;
		}
		leave = leaveService.findLeaveById(leave.getId());
		ActionContext.getContext().put("leave", leave);	
		System.out.println("getLeaveById=======================================getLeaveById");
		return "leaveView";
	}	
	
	public String searchLeave() throws Exception{
		System.out.println("searchLeave=======================================searchLeave");
		DetachedCriteria dc = DetachedCriteria.forClass(Leave.class);
		dc.setProjection(Projections.count("id"));
		Employee employee = getSessionEmployee();
		Integer positionId = getSessionPositionId();
		if(positionId==1||positionId==4){
			dc.add(Restrictions.eq("creator.sn",employee.getSn()));
		}else if(positionId==2){
			dc.add(Restrictions.eq("nextDeal.sn",employee.getSn()));
		}
		if(StringUtils.isNotBlank(startDate)){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = simpleDateFormat.parse(startDate);
				dc.add(Restrictions.ge("startTime",date));					
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(StringUtils.isNotBlank(endDate)){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = simpleDateFormat.parse(endDate);
				dc.add(Restrictions.lt("endTime",date));				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		dc.addOrder(Order.desc("createTime"));
		Page page = leaveService.findAllLeave(dc,pageNo);
		ActionContext.getContext().put("page", page);
		System.out.println("searchLeave=======================================searchLeave");
		return "leaveList";		
	}
	public String toEdit(){
		System.out.println("toEdit=======================================toEdit");
		System.out.println("toEdit=======================================toEdit");
		return "leaveEdit";
	}
	public String toCheck() throws Exception{	
		System.out.println("toCheck=======================================toCheck");
		if(leave==null||leave.getId()==null){
			return NONE;
		}
		leave = leaveService.findLeaveById(leave.getId());
		ActionContext.getContext().put("leave", leave);	
		System.out.println("toCheck=======================================toCheck");
		return "leaveCheck";
	}
	public String saveLeave() throws Exception{
		System.out.println("saveLeave=======================================saveLeave");
		leave.setCreateTime(new Date());
		leave.setModifyTime(new Date());
		leave.setStatus("´ýÉóÅú");
		leaveService.saveLeave(leave);
		System.out.println("saveLeave=======================================saveLeave");
		return "chain";
	}	
}
