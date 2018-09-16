package cn.jboa.service;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import cn.jboa.common.Page;
import cn.jboa.entity.ClaimVoucher;
import cn.jboa.entity.ClaimVoucherDetail;

public interface ClaimVoucherService {
	public void addClaimVoucher(ClaimVoucher claimVoucher) throws Exception;
	public void deleteClaimVoucherById(ClaimVoucher claimVoucher) throws Exception;
	public void updateClaimVoucher(ClaimVoucher claimVoucher,List<ClaimVoucherDetail> detailList) throws Exception;
	public ClaimVoucher findClaimVoucherById(Long id) throws Exception;
	/*public List<ClaimVoucher> findAllClaimVoucher(DetachedCriteria dCriteria) throws Exception;   */ 
	public Page findAllClaimVoucher(DetachedCriteria dCriteria,Integer num) throws Exception;
	public Map<String,String> getAllStatusMap() throws Exception;
}
