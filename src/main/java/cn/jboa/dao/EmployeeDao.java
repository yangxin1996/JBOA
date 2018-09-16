package cn.jboa.dao;

import java.util.List;

import cn.jboa.entity.Employee;

public interface EmployeeDao extends BaseDao<Employee>{

	List<Employee> findEmployeeManager(Employee employee) throws Exception;
	
}
