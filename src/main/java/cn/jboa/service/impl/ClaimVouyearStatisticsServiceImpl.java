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
import cn.jboa.dao.ClaimVouYearStatisticsDao;
import cn.jboa.dao.ClaimVoucherDao;
import cn.jboa.entity.ClaimVoucher;
import cn.jboa.entity.ClaimVouyearStatistics;
import cn.jboa.service.ClaimVouyearStatisticsService;

@Service("claimVouyearStatisticsService")
public class ClaimVouyearStatisticsServiceImpl implements ClaimVouyearStatisticsService{

	@Resource(name="claimVouYearStatisticsDao")
	private ClaimVouYearStatisticsDao claimVouyearStatisticsDao;
	@Resource(name="claimVoucherDao")
	private ClaimVoucherDao claimVoucherDao;
	
	@Override
	public Page findDeptStatisticsByYear(DetachedCriteria dCriteria, Integer num) throws Exception{
		// TODO Auto-generated method stub
		int currentPageNum = 1;
		if(num!=null){
			if(num>1){
				currentPageNum = num;	
			}
		}
		int totalRecods = claimVouyearStatisticsDao.findTotalRecords(dCriteria);	
		Page page = new Page(currentPageNum,totalRecods);	
		dCriteria.setProjection(null);
		List<ClaimVouyearStatistics> records = claimVouyearStatisticsDao.findAll(dCriteria, page.getStartIndex(), page.getPageSize());
		page.setRecords(records);		
		return page;
	}

	@Override
	public List<ClaimVoucher> findDeptStatisticsDetailByYear(int year, Integer departmentId) throws Exception{
		DetachedCriteria dc = DetachedCriteria.forClass(ClaimVoucher.class);
		String startYear = String.valueOf(year)+"-"+String.valueOf(1);
		String endYear = String.valueOf(year+1)+"-"+String.valueOf(1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
		try {
			Date sdate = simpleDateFormat.parse(startYear);
			Date edate = simpleDateFormat.parse(endYear);
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

/*	@Override
	public List<ClaimVoucher> findCompStatisticsByMonth(int year, Integer departmentId) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}*/

/*	@Override
	public List<ClaimVoucher> findCompStatisticsDetailByMonth(int year, Integer departmentId) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public List<ClaimVouyearStatistics> findCompStatisticsByYear(DetachedCriteria dCriteria) throws Exception{
		// TODO Auto-generated method stub
		return claimVouyearStatisticsDao.findAll(dCriteria);
	}

}
