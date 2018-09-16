package cn.jboa.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.jboa.common.Page;
import cn.jboa.entity.Employee;

public interface EmployeeService {
	public Employee login(Employee employee) throws Exception;
	public List<Employee> findEmployeeManager(Employee temp) throws Exception;
	/*public List<Employee> findAllEmployee(DetachedCriteria dCriteria) throws Exception;*/
	public Page findAllEmployee(DetachedCriteria dCriteria,Integer num) throws Exception;
	public void saveEmployee(Employee employee) throws Exception;
}
