package cn.jboa.dao.impl;

import org.springframework.stereotype.Repository;

import cn.jboa.dao.CheckResultDao;
import cn.jboa.entity.CheckResult;

@Repository("checkResultDao")
public class CheckResultDaoImpl extends BaseDaoImpl<CheckResult> implements CheckResultDao{

	public CheckResultDaoImpl() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

}
