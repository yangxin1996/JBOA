package cn.jboa.service;

import cn.jboa.entity.CheckResult;

public interface CheckResultService {

	public void saveCheckResult(CheckResult checkResult,String nextDealSn) throws Exception;

}
