//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.jboa.entity;

import java.io.Serializable;
import java.util.Date;


public class CheckResult implements Serializable {
    private static final long serialVersionUID = -6927459782166236900L;
    private Long id;
    private Long claimId;
    private Date checkTime;
    private String result;
    private String comment;
    private Employee checkEmployee;

    public CheckResult() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCheckTime() {
        return this.checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getClaimId() {
        return this.claimId;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }

    public Employee getCheckEmployee() {
        return this.checkEmployee;
    }

    public void setCheckEmployee(Employee checkEmployee) {
        this.checkEmployee = checkEmployee;
    }
    
}
