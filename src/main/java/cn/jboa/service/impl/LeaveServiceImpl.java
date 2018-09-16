package cn.jboa.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import cn.jboa.common.Page;
import cn.jboa.dao.LeaveDao;
import cn.jboa.entity.Leave;
import cn.jboa.service.LeaveService;

@Service("leaveService")
public class LeaveServiceImpl implements LeaveService{

	@Resource(name="leaveDao")
	private LeaveDao leaveDao;
	
	@Override
	public void saveLeave(Leave leave) throws Exception{
		// TODO Auto-generated method stub
		leaveDao.save(leave);
	}

	@Override
	public void deleteLeaveById(Long id) throws Exception{
		// TODO Auto-generated method stub
	}

	@Override
	public void updateLeave(Leave leave) throws Exception{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Leave findLeaveById(Long id) throws Exception{
		// TODO Auto-generated method stub
		return leaveDao.findById(id);
	}

/*	@Override
	public List<Leave> findAllLeave(DetachedCriteria dCriteria) throws Exception{
		// TODO Auto-generated method stub
		return leaveDao.findAll(dCriteria);
	}*/

	@Override
    public Map<String, String> getLeaveTypeMap() throws Exception{
        Map<String, String> leaveMap = new LinkedHashMap<String, String>();
        leaveMap.put("Äê¼Ù", "Äê¼Ù");
        leaveMap.put("ÊÂ¼Ù", "ÊÂ¼Ù");
        leaveMap.put("»é¼Ù", "»é¼Ù");
        leaveMap.put("²ú¼Ù", "²ú¼Ù");
        leaveMap.put("²¡¼Ù", "²¡¼Ù");
        return leaveMap;
    }

	@Override
	public Page findAllLeave(DetachedCriteria dCriteria, Integer num) throws Exception{
		// TODO Auto-generated method stub
		int currentPageNum = 1;
		if(num!=null){
			if(num>1){
				currentPageNum = num;	
			}
		}
		int totalRecods = leaveDao.findTotalRecords(dCriteria);	
		Page page = new Page(currentPageNum,totalRecods);	
		dCriteria.setProjection(null);
		List<Leave> records = leaveDao.findAll(dCriteria, page.getStartIndex(), page.getPageSize());	
		page.setRecords(records);		
		return page;
	}

}
