package cn.jboa.interceptor;
 
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import cn.jboa.entity.Employee;
 
public class LoginInterceptor implements Interceptor{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
 
	@Override
	public void init() {
		// TODO Auto-generated method stub
	}
 
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		Employee employee = (Employee) ActionContext.getContext().getSession().get("employee");
		if(employee==null){
			System.out.println("∑«∑®∑√Œ ");
			return "nopage";
		}
		String str=invocation.invoke();
		return str;
	}
 
}
