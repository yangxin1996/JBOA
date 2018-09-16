package cn.jboa.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.jboa.common.Page;
import cn.jboa.entity.ClaimVoucher;
import cn.jboa.entity.ClaimVouyearStatistics;

public interface ClaimVouyearStatisticsService {
	
	Page findDeptStatisticsByYear(DetachedCriteria dCriteria, Integer num) throws Exception;
	List<ClaimVoucher> findDeptStatisticsDetailByYear(int year, Integer departmentId) throws Exception;
/*	List<ClaimVoucher> findCompStatisticsByMonth(int year, Integer departmentId) throws Exception;
	List<ClaimVoucher> findCompStatisticsDetailByMonth(int year, Integer departmentId) throws Exception;*/
	List<ClaimVouyearStatistics> findCompStatisticsByYear(DetachedCriteria dc) throws Exception;
	
}
