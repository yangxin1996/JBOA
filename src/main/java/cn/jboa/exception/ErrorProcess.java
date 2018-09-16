package cn.jboa.exception ;
 
//�Զ����쳣
public class ErrorProcess extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public ErrorProcess(String message)
	{
		super(message);
		this.message = message ;
	}
 
	public String getMessage() {
		return message;
	}
 
	public void setMessage(String message) {
		this.message = message;
	}
	
 
}
