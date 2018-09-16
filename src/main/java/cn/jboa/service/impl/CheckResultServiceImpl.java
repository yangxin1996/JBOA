package cn.jboa.service.impl;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import cn.jboa.dao.CheckResultDao;
import cn.jboa.dao.ClaimVoucherDao;
import cn.jboa.entity.CheckResult;
import cn.jboa.entity.ClaimVoucher;
import cn.jboa.entity.Employee;
import cn.jboa.service.CheckResultService;

@Service("checkResultService")
public class CheckResultServiceImpl implements CheckResultService{

	@Resource(name="checkResultDao")
	private CheckResultDao checkResultDao;
	@Resource(name="claimVoucherDao")
	private ClaimVoucherDao claimVoucherDao;
	
	@Override
	public void saveCheckResult(CheckResult checkResult,String nextDealSn) throws Exception{
		// TODO Auto-generated method stub
		ClaimVoucher claimVoucher = claimVoucherDao.findById(checkResult.getClaimId());
		if("打回".equals(checkResult.getResult())){
			claimVoucher.setStatus("已打回");
			claimVoucher.setNextDeal(null);
			//打回到初始状态
		}else if("拒绝".equals(checkResult.getResult())){
			claimVoucher.setStatus("已终止");
			claimVoucher.setNextDeal(null);
			//拒绝终止
		}else{
	    	HttpSession session = ServletActionContext.getRequest().getSession();
	    	Employee employee = (Employee)session.getAttribute("employee");
	    	//@SuppressWarnings("unchecked")
			//List<Employee> manager= (List<Employee>) session.getAttribute("manager");	
	    	Employee manager = new Employee();
	    	manager.setSn(nextDealSn);
			if(employee.getSysPosition().getId()==2){
				claimVoucher.setStatus("已审阅");
				claimVoucher.setNextDeal(manager);
			}else if(employee.getSysPosition().getId()==3){
				claimVoucher.setStatus("已审批");
				claimVoucher.setNextDeal(manager);
			}else{
				claimVoucher.setStatus("已付款");
				claimVoucher.setNextDeal(null);
			}
		}
		claimVoucherDao.save(claimVoucher);
		checkResultDao.save(checkResult);
	}

}
