//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.jboa.entity;

import java.io.Serializable;
import java.util.Date;

public class ClaimVouyearStatistics implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private Double totalCount;
    private int year;
    private Date modifyTime;
    private Department dept;

    public ClaimVouyearStatistics() {
    }

    public ClaimVouyearStatistics(Long id, Double totalCount, Short year, Date modifyTime) {
        this.id = id;
        this.totalCount = totalCount;
        this.year = year;
        this.modifyTime = modifyTime;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(Double totalCount) {
        this.totalCount = totalCount;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Date getModifyTime() {
        return this.modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Department getDept() {
        return this.dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }
    
}
