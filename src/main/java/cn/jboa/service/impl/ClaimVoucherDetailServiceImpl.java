package cn.jboa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jboa.dao.ClaimVoucherDetailDao;
import cn.jboa.entity.ClaimVoucherDetail;
import cn.jboa.service.ClaimVoucherDetailService;

@Service("claimVoucherDetailService")
public class ClaimVoucherDetailServiceImpl implements ClaimVoucherDetailService{

	@Resource(name="claimVoucherDetailDao")
	private ClaimVoucherDetailDao claimVoucherDetailDao;
	
	@Override
	public void saveClaimVoucherDetail(ClaimVoucherDetail claimVoucherDetail) throws Exception{
		// TODO Auto-generated method stub
		claimVoucherDetailDao.save(claimVoucherDetail);
	}

	@Override
	public void saveClaimVoucherDetailList(List<ClaimVoucherDetail> list) throws Exception{
		// TODO Auto-generated method stub
		claimVoucherDetailDao.saveList(list);
	}

	@Override
	public void deleteClaimVoucherDetailList(List<ClaimVoucherDetail> list) throws Exception{
		// TODO Auto-generated method stub
		claimVoucherDetailDao.deleteList(list);
	}

}
