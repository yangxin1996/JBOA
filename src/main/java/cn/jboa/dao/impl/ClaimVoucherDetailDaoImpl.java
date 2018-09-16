package cn.jboa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.jboa.dao.ClaimVoucherDetailDao;
import cn.jboa.entity.ClaimVoucherDetail;

@Repository("claimVoucherDetailDao")
public class ClaimVoucherDetailDaoImpl extends BaseDaoImpl<ClaimVoucherDetail> implements ClaimVoucherDetailDao{

	public ClaimVoucherDetailDaoImpl() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void saveList(List<ClaimVoucherDetail> list) {
		// TODO Auto-generated method stub
		for (int i = 0; i < list.size(); i++) {
			ClaimVoucherDetail claimVoucherDetail = (ClaimVoucherDetail) list.get(i); 
			hibernateTemplate.saveOrUpdate(claimVoucherDetail);
			if (i % 10 == 0) {	
			    this.hibernateTemplate.flush();
			    this.hibernateTemplate.clear();
			}
		}
	}

	@Override
	public void deleteList(List<ClaimVoucherDetail> list) {
		// TODO Auto-generated method stub
		for (int i = 0; i < list.size(); i++) {
			ClaimVoucherDetail claimVoucherDetail = (ClaimVoucherDetail) list.get(i); 
			hibernateTemplate.delete(claimVoucherDetail);
			if (i % 10 == 0) {
			    this.hibernateTemplate.flush();
			    this.hibernateTemplate.clear();
			}
		}		
	}

}
