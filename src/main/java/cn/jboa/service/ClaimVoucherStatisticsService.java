package cn.jboa.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.jboa.common.Page;
import cn.jboa.entity.ClaimVoucher;
import cn.jboa.entity.ClaimVoucherStatistics;

public interface ClaimVoucherStatisticsService {
	List<ClaimVoucher> findDeptStatisticsDetailByMonth(int year, int currMonth, Integer departmentId) throws Exception;
	Page findDeptStatisticsByMonth(DetachedCriteria dCriteria,Integer num) throws Exception;
	List<ClaimVoucherStatistics> findCompStatisticsByMonth(DetachedCriteria dCriteria) throws Exception;
	List<ClaimVoucherStatistics> findCompStatisticsDetailByMonth(int year, int currMonth) throws Exception;
}
