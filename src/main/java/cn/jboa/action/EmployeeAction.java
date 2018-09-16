package cn.jboa.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import cn.jboa.entity.Employee;
import cn.jboa.service.EmployeeService;


@Controller("employeeAction")
@Scope("prototype")
public class EmployeeAction extends BaseAction implements ModelDriven<Employee>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource(name="employeeService")
	private EmployeeService employeeService;
	private Employee employee = new Employee();
	private String msg;
	private String random;
	
	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public Employee getModel() {
		// TODO Auto-generated method stub
		return employee;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public String login() throws Exception{
		Employee temp = employeeService.login(employee);	
		String code = (String) ActionContext.getContext().getSession().get("securityCode");	
		if(random!=null&&random.equals(code)){
		}else{
			msg="验证码错误!";
			return INPUT;
		}
		if(temp==null){
			msg="验证失败!";
			return INPUT;
		}else{
			//查询所有上级
			List<Employee> manager = employeeService.findEmployeeManager(temp);
		    ActionContext.getContext().getSession().put("employee", temp);
		    ActionContext.getContext().getSession().put("employee_position", temp.getSysPosition().getNameCn());
		    ActionContext.getContext().getSession().put("manager", manager);
			return SUCCESS;
		}
	}
}
