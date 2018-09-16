package cn.jboa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import cn.jboa.common.Page;
import cn.jboa.dao.EmployeeDao;
import cn.jboa.entity.Employee;
import cn.jboa.service.EmployeeService;
import cn.jboa.util.MD5;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{

	@Resource(name="employeeDao")
	private EmployeeDao employeeDao;

	@Override
	public Employee login(Employee employee) throws Exception{
		// TODO Auto-generated method stub
		if(employee==null||employee.getSn()==null||employee.getPassword()==null){
			return null;
		}		
		Employee temp = employeeDao.findById(employee.getSn());
		if(temp!=null&&"ÔÚÖ°".equals(temp.getStatus())&&MD5.getInstance().getMD5(employee.getPassword()).equals(temp.getPassword())){
			return temp;
		}
		return null;
	}

/*	@Override
	public List<Employee> findAllEmployee(DetachedCriteria dCriteria) throws Exception{
		// TODO Auto-generated method stub
		return employeeDao.findAll(dCriteria);
	}*/

	@Override
	public void saveEmployee(Employee employee) throws Exception{
		// TODO Auto-generated method stub
		employeeDao.save(employee);
	}

	@Override
	public List<Employee> findEmployeeManager(Employee employee) throws Exception{
		// TODO Auto-generated method stub
		return employeeDao.findEmployeeManager(employee);
	}

	@Override
	public Page findAllEmployee(DetachedCriteria dCriteria, Integer num) throws Exception{
		// TODO Auto-generated method stub
		int currentPageNum = 1;
		if(num!=null){
			if(num>1){
				currentPageNum = num;	
			}
		}
		int totalRecods = employeeDao.findTotalRecords(dCriteria);	
		Page page = new Page(currentPageNum,totalRecods);	
		dCriteria.setProjection(null);
		List<Employee> records = employeeDao.findAll(dCriteria, page.getStartIndex(), page.getPageSize());	
		page.setRecords(records);		
		return page;
	}
}
