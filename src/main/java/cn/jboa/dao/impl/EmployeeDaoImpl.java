package cn.jboa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.jboa.dao.EmployeeDao;
import cn.jboa.entity.Employee;


@Repository("employeeDao")
public class EmployeeDaoImpl extends BaseDaoImpl<Employee> implements EmployeeDao{

	public EmployeeDaoImpl() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Employee> findEmployeeManager(Employee employee) throws Exception{
		// TODO Auto-generated method stub	
		if(employee.getSysPosition().getId()==1||employee.getSysPosition().getId()==4){
			@SuppressWarnings("unchecked")
			List<Employee> list = (List<Employee>) hibernateTemplate.find("from Employee e where e.sysDepartment.id="+employee.getSysDepartment().getId()+" and e.sysPosition.id=2");
			return list;
		}else{
			@SuppressWarnings("unchecked")
			List<Employee> list = (List<Employee>) hibernateTemplate.find("from Employee e where e.sysPosition.id="+(employee.getSysPosition().getId()+1)+"");
			return list;
		}

	}

}
