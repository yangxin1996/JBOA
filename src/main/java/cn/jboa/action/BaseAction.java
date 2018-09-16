package cn.jboa.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.jboa.entity.Employee;
import cn.jboa.entity.Position;

@Controller("baseAction")
@Scope("prototype")
public class BaseAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Employee getSessionEmployee() throws Exception{
    	HttpSession session = ServletActionContext.getRequest().getSession();
    	Employee employee = (Employee)session.getAttribute("employee");		
		return employee;
	}
	public Integer getSessionPositionId() throws Exception{
    	HttpSession session = ServletActionContext.getRequest().getSession();
    	Employee employee = (Employee)session.getAttribute("employee");	
    	if(employee!=null){
    		Position position = employee.getSysPosition();
    		if(position!=null){
    			return position.getId();
    		}
    	}
    	return null;
	}	
	public List<Employee> getEmployeeMannager() throws Exception{
    	HttpSession session = ServletActionContext.getRequest().getSession();
    	@SuppressWarnings("unchecked")
		List<Employee> manager= (List<Employee>) session.getAttribute("manager");		
		return manager;
	}
}
