package cn.jboa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.jboa.dao.ClaimVoucherStatisticsDao;
import cn.jboa.entity.ClaimVoucherStatistics;

@Repository("claimVoucherStatisticsDao")
public class ClaimVoucherStatisticsDaoImpl extends BaseDaoImpl<ClaimVoucherStatistics> implements ClaimVoucherStatisticsDao{


	public ClaimVoucherStatisticsDaoImpl() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ClaimVoucherStatistics> findCompStatisticsGroupByMonth(int year, int startMonth, int endMonth) throws Exception {
		// TODO Auto-generated method stub
/*		String sql="select sum(total_count) total_count,year,month from oa.biz_claim_voucher_statistics where year=? and month between ? and ? group by year,month order by year,month asc";
		List<ClaimVoucherStatistics> list = (List<ClaimVoucherStatistics>) hibernateTemplate.find("from Employee e where e.sysDepartment.id="+employee.getSysDepartment().getId()+" and e.sysPosition.id="+(employee.getSysPosition().getId()+1)+"");
		return list;*/
		return null;
	}
}
