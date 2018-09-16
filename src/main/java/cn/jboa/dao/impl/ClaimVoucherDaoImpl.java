package cn.jboa.dao.impl;

import org.springframework.stereotype.Repository;

import cn.jboa.dao.ClaimVoucherDao;
import cn.jboa.entity.ClaimVoucher;

@Repository("claimVoucherDao")
public class ClaimVoucherDaoImpl extends BaseDaoImpl<ClaimVoucher> implements ClaimVoucherDao{

	public ClaimVoucherDaoImpl() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

}
