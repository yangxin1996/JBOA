package cn.jboa.dao.impl;

import org.springframework.stereotype.Repository;

import cn.jboa.dao.ClaimVouYearStatisticsDao;
import cn.jboa.entity.ClaimVouyearStatistics;

@Repository("claimVouYearStatisticsDao")
public class ClaimVouYearStatisticsDaoImpl extends BaseDaoImpl<ClaimVouyearStatistics>  implements ClaimVouYearStatisticsDao{

	public ClaimVouYearStatisticsDaoImpl() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

}
