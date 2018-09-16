package cn.jboa.service;

import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import cn.jboa.common.Page;
import cn.jboa.entity.Leave;

public interface LeaveService {
	public void saveLeave(Leave leave) throws Exception;
	public void deleteLeaveById(Long id) throws Exception;
	public void updateLeave(Leave leave) throws Exception;
	public Leave findLeaveById(Long id) throws Exception;
	/*public List<Leave> findAllLeave(DetachedCriteria dCriteria) throws Exception;*/
	public Page findAllLeave(DetachedCriteria dCriteria,Integer num) throws Exception;
	public Map<String, String> getLeaveTypeMap() throws Exception;
}
