package cn.jboa.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import cn.jboa.common.Page;
import cn.jboa.dao.ClaimVoucherDao;
import cn.jboa.dao.ClaimVoucherStatisticsDao;
import cn.jboa.entity.ClaimVoucher;
import cn.jboa.entity.ClaimVoucherStatistics;
import cn.jboa.service.ClaimVoucherStatisticsService;

@Service("claimVoucherStatisticsService")
public class ClaimVoucherStatisticsServiceImpl implements ClaimVoucherStatisticsService{
	
	@Resource(name="claimVoucherStatisticsDao")
	private ClaimVoucherStatisticsDao claimVoucherStatisticsDao;
	
	@Resource(name="claimVoucherDao")
	private ClaimVoucherDao claimVoucherDao;
	//@Resource(name="departmentDao")
	//private DepartmentDao departmentDao;	
	
	@Override
	public Page findDeptStatisticsByMonth(DetachedCriteria dCriteria, Integer num) throws Exception{
		// TODO Auto-generated method stub
		int currentPageNum = 1;
		if(num!=null){
			if(num>1){
				currentPageNum = num;	
			}
		}
		int totalRecods = claimVoucherStatisticsDao.findTotalRecords(dCriteria);	
		Page page = new Page(currentPageNum,totalRecods);	
		dCriteria.setProjection(null);
		List<ClaimVoucherStatistics> records = claimVoucherStatisticsDao.findAll(dCriteria, page.getStartIndex(), page.getPageSize());
		page.setRecords(records);		
		return page;
	}

	@Override
	public List<ClaimVoucher> findDeptStatisticsDetailByMonth(int year, int selectMonth, Integer departmentId) throws Exception{
		// TODO Auto-generated method stub
		DetachedCriteria dc = DetachedCriteria.forClass(ClaimVoucher.class);
		String startDate = String.valueOf(year)+"-"+String.valueOf(selectMonth);
		String endDate;
		if(selectMonth==12){
			endDate = String.valueOf(year+1)+"-"+String.valueOf(selectMonth);
		}else{
			endDate = String.valueOf(year)+"-"+String.valueOf(selectMonth+1);
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
		try {
			Date sdate = simpleDateFormat.parse(startDate);
			Date edate = simpleDateFormat.parse(endDate);
			dc.add(Restrictions.ge("modifyTime",sdate));
			dc.add(Restrictions.lt("modifyTime",edate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dc.add(Restrictions.eq("status","ÒÑ¸¶¿î"));
		dc.createAlias("creator", "c");
		dc.add(Restrictions.eq("c.sysDepartment.id",departmentId));
		return claimVoucherDao.findAll(dc);
	}

	@Override
	public List<ClaimVoucherStatistics> findCompStatisticsByMonth(DetachedCriteria dCriteria) throws Exception{
		// TODO Auto-generated method stub
		return claimVoucherStatisticsDao.findAll(dCriteria);
	}

	@Override
	public List<ClaimVoucherStatistics> findCompStatisticsDetailByMonth(int year, int currMonth) throws Exception{
		// TODO Auto-generated method stub
		DetachedCriteria dCriteria = DetachedCriteria.forClass(ClaimVoucherStatistics.class);
		dCriteria.add(Restrictions.eq("year", year));
		dCriteria.add(Restrictions.eq("month", currMonth));
		return claimVoucherStatisticsDao.findAll(dCriteria);
	}
}
