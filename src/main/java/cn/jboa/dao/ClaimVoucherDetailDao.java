package cn.jboa.dao;

import java.util.List;

import cn.jboa.entity.ClaimVoucherDetail;

public interface ClaimVoucherDetailDao extends BaseDao<ClaimVoucherDetail>{
	public void saveList(List<ClaimVoucherDetail> list) throws Exception;
	public void deleteList(List<ClaimVoucherDetail> list) throws Exception;
}
