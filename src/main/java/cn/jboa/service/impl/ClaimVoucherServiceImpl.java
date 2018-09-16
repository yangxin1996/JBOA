package cn.jboa.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import cn.jboa.common.Page;
import cn.jboa.dao.ClaimVoucherDao;
import cn.jboa.dao.ClaimVoucherDetailDao;
import cn.jboa.dao.EmployeeDao;
import cn.jboa.entity.ClaimVoucher;
import cn.jboa.entity.ClaimVoucherDetail;
import cn.jboa.service.ClaimVoucherService;

@Service("claimVoucherService")
public class ClaimVoucherServiceImpl implements ClaimVoucherService{

	@Resource(name="claimVoucherDao")
	private ClaimVoucherDao claimVoucherDao;
	@Resource(name="employeeDao")
	private EmployeeDao employeeDao;
	@Resource(name="claimVoucherDetailDao")
	private ClaimVoucherDetailDao claimVoucherDetailDao;	
	
	@Override
	public void addClaimVoucher(ClaimVoucher claimVoucher) throws Exception{
		// TODO Auto-generated method stub
/*		if("新创建".equals(claimVoucher.getStatus())){
	    	HttpSession session = ServletActionContext.getRequest().getSession();
	    	Employee employee = (Employee)session.getAttribute("employee");	
	    	employee = employeeDao.findById(employee.getSn());
			claimVoucher.setNextDeal(null);
			claimVoucherDao.save(claimVoucher);
			return;
		}*/
		if("新创建".equals(claimVoucher.getStatus())){
			claimVoucher.setNextDeal(null);
		}
		claimVoucherDao.save(claimVoucher);
	}

	@Override
	public void deleteClaimVoucherById(ClaimVoucher claimVoucher) throws Exception{
		// TODO Auto-generated method stub
		claimVoucher = claimVoucherDao.findById(claimVoucher.getId());
		claimVoucherDao.delete(claimVoucher);
	}

	@Override
	public void updateClaimVoucher(ClaimVoucher claimVoucher,List<ClaimVoucherDetail> detailList) throws Exception{
		// TODO Auto-generated method stub
		//claimVoucher = claimVoucherDao.findById(claimVoucher.getId());
		//claimVoucherDetailDao.deleteList(temp.getDetailList());
		//List<ClaimVoucherDetail> a = new ArrayList<ClaimVoucherDetail>();
		//claimVoucher.setDetailList(null);
		//claimVoucher.getDetailList().clear();
		//claimVoucher.setDetailList(detailList);
		if("新创建".equals(claimVoucher.getStatus())){
			claimVoucher.setNextDeal(null);
		}
		claimVoucher.getDetailList().clear();
		for(ClaimVoucherDetail c:detailList){
			c.setBizClaimVoucher(claimVoucher);
			claimVoucher.getDetailList().add(c);
		}
		claimVoucherDao.update(claimVoucher);
	}

	@Override
	public ClaimVoucher findClaimVoucherById(Long id) throws Exception{
		// TODO Auto-generated method stub
		return claimVoucherDao.findById(id);
	}

/*	@Override
	public List<ClaimVoucher> findAllClaimVoucher(DetachedCriteria dCriteria) throws Exception{
		// TODO Auto-generated method stub
		return claimVoucherDao.findAll(dCriteria);
	}*/

	@Override
    public Map<String, String> getAllStatusMap() throws Exception{
        Map<String, String> statusMap = new LinkedHashMap<String, String>();
        statusMap.put("新创建", "新创建");
        statusMap.put("已提交", "已提交");
        statusMap.put("已打回", "已打回");
        statusMap.put("待审批", "待审批");
        statusMap.put("已审批", "已审批");
        statusMap.put("已付款", "已付款");
        statusMap.put("已终止", "已终止");
        return statusMap;
    }

	@Override
	public Page findAllClaimVoucher(DetachedCriteria dCriteria, Integer num) throws Exception{
		// TODO Auto-generated method stub
		int currentPageNum = 1;
		if(num!=null){
			if(num>1){
				currentPageNum = num;	
			}
		}		
		int totalRecods = claimVoucherDao.findTotalRecords(dCriteria);	
		Page page = new Page(currentPageNum,totalRecods);	
		dCriteria.setProjection(null);
		List<ClaimVoucher> records = claimVoucherDao.findAll(dCriteria, page.getStartIndex(), page.getPageSize());
		page.setRecords(records);		
		return page;
	}

}
