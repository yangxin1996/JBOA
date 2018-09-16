package cn.jboa.service;

import java.util.List;

import cn.jboa.entity.ClaimVoucherDetail;

public interface ClaimVoucherDetailService {
	public void saveClaimVoucherDetail(ClaimVoucherDetail claimVoucherDetail) throws Exception;
	public void saveClaimVoucherDetailList(List<ClaimVoucherDetail> list) throws Exception;
	public void deleteClaimVoucherDetailList(List<ClaimVoucherDetail> list) throws Exception;
}
