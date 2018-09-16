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
		if("���".equals(checkResult.getResult())){
			claimVoucher.setStatus("�Ѵ��");
			claimVoucher.setNextDeal(null);
			//��ص���ʼ״̬
		}else if("�ܾ�".equals(checkResult.getResult())){
			claimVoucher.setStatus("����ֹ");
			claimVoucher.setNextDeal(null);
			//�ܾ���ֹ
		}else{
	    	HttpSession session = ServletActionContext.getRequest().getSession();
	    	Employee employee = (Employee)session.getAttribute("employee");
	    	//@SuppressWarnings("unchecked")
			//List<Employee> manager= (List<Employee>) session.getAttribute("manager");	
	    	Employee manager = new Employee();
	    	manager.setSn(nextDealSn);
			if(employee.getSysPosition().getId()==2){
				claimVoucher.setStatus("������");
				claimVoucher.setNextDeal(manager);
			}else if(employee.getSysPosition().getId()==3){
				claimVoucher.setStatus("������");
				claimVoucher.setNextDeal(manager);
			}else{
				claimVoucher.setStatus("�Ѹ���");
				claimVoucher.setNextDeal(null);
			}
		}
		claimVoucherDao.save(claimVoucher);
		checkResultDao.save(checkResult);
	}

}
