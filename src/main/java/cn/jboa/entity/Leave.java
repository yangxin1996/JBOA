//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.jboa.entity;

import java.io.Serializable;
import java.util.Date;

public class Leave implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private Employee creator;
    private Date startTime;
    private Date endTime;
    private Double leaveDay;
    private String reason;
    private String status;
    private String leaveType;
    private Employee nextDeal;
    private String approveOpinion;
    private Date createTime;
    private Date modifyTime;

    public Leave() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getLeaveDay() {
        return this.leaveDay;
    }

    public void setLeaveDay(Double leaveday) {
        this.leaveDay = leaveday;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLeaveType() {
        return this.leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getApproveOpinion() {
        return this.approveOpinion;
    }

    public void setApproveOpinion(String approveOpinion) {
        this.approveOpinion = approveOpinion;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return this.modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
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
 
}
