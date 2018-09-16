package cn.jboa.action;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.jboa.entity.CheckResult;
import cn.jboa.service.CheckResultService;

@Controller("checkResultAction")
@Scope("prototype")
public class CheckResultAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="checkResultService")
	private CheckResultService checkResultService;
	
	private CheckResult checkResult;
	private String nextDealSn;
	
	public String getNextDealSn() {
		return nextDealSn;
	}
	public void setNextDealSn(String nextDealSn) {
		this.nextDealSn = nextDealSn;
	}
	public CheckResult getCheckResult() {
		return checkResult;
	}
	public void setCheckResult(CheckResult checkResult) {
		this.checkResult = checkResult;
	}
	
	public String checkClaimVoucher() throws Exception{
		System.out.println("checkClaimVoucher=======================================checkClaimVoucher");	
        checkResult.setCheckEmployee(getSessionEmployee());
        checkResult.setCheckTime(new Date());
        checkResultService.saveCheckResult(checkResult,nextDealSn);
		System.out.println("checkClaimVoucher=======================================checkClaimVoucher");
        return "chain";
    }
}
