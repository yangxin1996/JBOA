//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.jboa.entity;

import java.io.Serializable;
import java.util.Date;

public class ClaimVoucherStatistics implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private Double totalCount;
    private int year;
    private int month;
    private Department department;
    private Date modifyTime;

    public ClaimVoucherStatistics() {
    }

    public ClaimVoucherStatistics(Long id, Double totalCount, Integer year, Integer month, Department department, Date modifyTime) {
        this.id = id;
        this.totalCount = totalCount;
        this.year = year;
        this.month = month;
        this.department = department;
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

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Date getModifyTime() {
        return this.modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    
}
