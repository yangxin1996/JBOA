package cn.jboa.dao.impl;

import org.springframework.stereotype.Repository;

import cn.jboa.dao.LeaveDao;
import cn.jboa.entity.Leave;

@Repository("leaveDao")
public class LeaveDaoImpl extends BaseDaoImpl<Leave> implements LeaveDao{

	public LeaveDaoImpl() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

}
