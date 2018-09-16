package cn.jboa.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import cn.jboa.entity.*;
import cn.jboa.service.ClaimVoucherDetailService;
import cn.jboa.service.ClaimVoucherService;

@Controller("claimVoucherAction")
@Scope("prototype")
public class ClaimVoucherAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name="claimVoucherService")
	private ClaimVoucherService claimVoucherService;
	@Resource(name="claimVoucherDetailService")
	private ClaimVoucherDetailService claimVoucherDetailService;
	
    private String startDate;
    private String endDate;	
    private int pageNo;
	private ClaimVoucher claimVoucher;
	private List<ClaimVoucherDetail> detailList;
	
	@SuppressWarnings("unused")
	private Map<String, String> statusMap;

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

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public Map<String, String> getStatusMap() throws Exception{
        return claimVoucherService.getAllStatusMap();
    }

	public ClaimVoucher getClaimVoucher() {
		return claimVoucher;
	}

	public void setClaimVoucher(ClaimVoucher claimVoucher) {
		this.claimVoucher = claimVoucher;
	}

	public List<ClaimVoucherDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<ClaimVoucherDetail> detailList) {
		this.detailList = detailList;
	}

	public String searchClaimVoucher() throws Exception{
		System.out.println("searchClaimVoucher=======================================searchClaimVoucher");
		DetachedCriteria dc = DetachedCriteria.forClass(ClaimVoucher.class);
		dc.setProjection(Projections.count("id"));
		Integer positionId = getSessionPositionId();
		String employeeSn = getSessionEmployee().getSn();
		if(positionId==1){
			dc.add(Restrictions.eq("creator.sn", employeeSn));
		}else{
			dc.add(Restrictions.eq("nextDeal.sn", employeeSn));
		}
		if(claimVoucher!=null){
			if(StringUtils.isNotBlank(claimVoucher.getStatus())){
				dc.add(Restrictions.eq("status", claimVoucher.getStatus()));
			}
			if(StringUtils.isNotBlank(startDate)){
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date date = simpleDateFormat.parse(startDate);
					dc.add(Restrictions.ge("createTime",date));					
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if(StringUtils.isNotBlank(endDate)){
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date date = simpleDateFormat.parse(endDate);
					dc.add(Restrictions.lt("createTime",date));				
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		dc.addOrder(Order.desc("createTime"));
		Page page = claimVoucherService.findAllClaimVoucher(dc,pageNo);
		ActionContext.getContext().put("page", page);
		System.out.println("searchClaimVoucher=======================================searchClaimVoucher");
		return "claimVoucherList";
	}

	public String getClaimVoucherById() throws Exception{
		System.out.println("getClaimVoucherById=======================================getClaimVoucherById");
		if(claimVoucher==null||claimVoucher.getId()==null){
			return NONE;
		}
		claimVoucher = claimVoucherService.findClaimVoucherById(claimVoucher.getId());
		ActionContext.getContext().put("claimVoucher", claimVoucher);	
		System.out.println("getClaimVoucherById=======================================getClaimVoucherById");
		return "claimVoucherView";
	}
	
	public String toUpdate() throws Exception{
		System.out.println("toUpdate=======================================toUpdate");
		claimVoucher = claimVoucherService.findClaimVoucherById(claimVoucher.getId());
		ActionContext.getContext().put("claimVoucher", claimVoucher);	
		System.out.println("toUpdate=======================================toUpdate");
		return "claimVoucherUpdate";
	}

	public String updateClaimVoucher() throws Exception{	
		System.out.println("updateClaimVoucher=======================================updateClaimVoucher");
		ClaimVoucher temp = claimVoucherService.findClaimVoucherById(claimVoucher.getId());
		temp.setEvent(claimVoucher.getEvent());
		temp.setStatus(claimVoucher.getStatus());
		temp.setModifyTime(new Date());
		temp.setTotalAccount(claimVoucher.getTotalAccount());	
		temp.setNextDeal(claimVoucher.getNextDeal());
		//temp.setDetailList(detailList);
		claimVoucherService.updateClaimVoucher(temp,detailList);
        System.out.println("updateClaimVoucher=======================================updateClaimVoucher");
		return "chain";
	}
	
	public String saveClaimVoucher() throws Exception{
		System.out.println("saveClaimVoucher=======================================saveClaimVoucher");
		claimVoucher.setCreator(getSessionEmployee());
		claimVoucher.setModifyTime(new Date());
		claimVoucher.setCreateTime(new Date());
		claimVoucher.setDetailList(detailList);
        claimVoucherService.addClaimVoucher(claimVoucher);
        System.out.println("saveClaimVoucher=======================================saveClaimVoucher");
		return "chain";
	}

	public String deleteClaimVoucherById() throws Exception{	
		System.out.println("deleteClaimVoucherById=======================================deleteClaimVoucherById");
		claimVoucherService.deleteClaimVoucherById(claimVoucher);
		System.out.println("deleteClaimVoucherById=======================================deleteClaimVoucherById");
		return "chain";
	}
	
	public String toCheck() throws Exception{	
		System.out.println("toCheck=======================================toCheck");
		claimVoucher = claimVoucherService.findClaimVoucherById(claimVoucher.getId());
		System.out.println("toCheck=======================================toCheck");
		return "claimVoucherCheck";
	}
	
	public String toAdd() throws Exception{
		System.out.println("toAdd=======================================toAdd");
		System.out.println("toAdd=======================================toAdd");
		return "claimVoucherEdit";
	}
	
}
