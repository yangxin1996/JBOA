//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.jboa.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClaimVoucher implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private Employee creator;
    private Employee nextDeal;
    private Date createTime;
    private String event;
    private Double totalAccount = 0.0D;
    private String status;
    private Date modifyTime;
    private List<ClaimVoucherDetail> detailList = new ArrayList<ClaimVoucherDetail>();
    private List<CheckResult> checkResultList = new ArrayList<CheckResult>();

	public ClaimVoucher() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getCreator() {
        return this.creator;
    }

    public void setCreator(Employee creator) {
        this.creator = creator;
    }

    public Employee getNextDeal() {
        return this.nextDeal;
    }

    public void setNextDeal(Employee nextDeal) {
        this.nextDeal = nextDeal;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEvent() {
        return this.event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Double getTotalAccount() {
        return this.totalAccount;
    }

    public void setTotalAccount(Double totalAccount) {
        this.totalAccount = totalAccount;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getModifyTime() {
        return this.modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public List<ClaimVoucherDetail> getDetailList() {
        return this.detailList;
    }

    public void setDetailList(List<ClaimVoucherDetail> detailList) {
    	if(detailList!=null){
    		for(ClaimVoucherDetail claimVoucherDetail : detailList) {
    			if(claimVoucherDetail!=null){
        			if(claimVoucherDetail.getBizClaimVoucher()==null){
        				claimVoucherDetail.setBizClaimVoucher(this);	
        			}
    			}
     		}
    	}  	
        this.detailList = detailList;
    }

    public List<CheckResult> getCheckResultList() {
        return this.checkResultList;
    }

    public void setCheckResultList(List<CheckResult> checkResultList) {
        this.checkResultList = checkResultList;
    }
   
}
