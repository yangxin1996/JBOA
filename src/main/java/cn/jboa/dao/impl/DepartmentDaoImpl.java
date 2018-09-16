package cn.jboa.dao.impl;

import org.springframework.stereotype.Repository;

import cn.jboa.dao.DepartmentDao;
import cn.jboa.entity.Department;

@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao{

	public DepartmentDaoImpl() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

}
