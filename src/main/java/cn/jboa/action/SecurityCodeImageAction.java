package cn.jboa.action;

import java.io.ByteArrayInputStream;
import java.util.Map;
 
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.jboa.util.SecurityCode;
import cn.jboa.util.SecurityImage;


@Controller("securityCodeImageAction")
@Scope("prototype")
public class SecurityCodeImageAction  extends ActionSupport  implements SessionAware{
	private static final long serialVersionUID = 1496691731440581303L;
	//ͼƬ��
    private ByteArrayInputStream imageStream;
    //session��
    private Map<String, Object> session ;
    
    public ByteArrayInputStream getImageStream() {
        return imageStream;
    }
    public void setImageStream(ByteArrayInputStream imageStream) {
        this.imageStream = imageStream;
    }
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String execute() throws Exception {
        //�������Hardģʽ�����Բ����ִ�Сд
        //String securityCode = SecurityCode.getSecurityCode(4,SecurityCodeLevel.Hard, false).toLowerCase();
        
        //��ȡĬ���ѶȺͳ��ȵ���֤��
        String securityCode = SecurityCode.getSecurityCode();
        imageStream = SecurityImage.getImageAsInputStream(securityCode);
        //����session��
        session.put("securityCode", securityCode);
        return SUCCESS;
    }
}

