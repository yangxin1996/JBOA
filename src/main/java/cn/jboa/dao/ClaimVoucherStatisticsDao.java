package cn.jboa.dao;

import java.util.List;

import cn.jboa.entity.ClaimVoucherStatistics;

public interface ClaimVoucherStatisticsDao extends BaseDao<ClaimVoucherStatistics>{
	List<ClaimVoucherStatistics> findCompStatisticsGroupByMonth(int year,int startMonth,int endMonth) throws Exception;
}
