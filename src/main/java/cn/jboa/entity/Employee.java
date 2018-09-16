//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cn.jboa.entity;

import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1035721299787840813L;
    private String sn;
    private String password;
    private String name;
    private String status;
    private Department sysDepartment;
    private Position sysPosition;

    public Employee() {
    }

    public String getSn() {
        return this.sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Department getSysDepartment() {
        return this.sysDepartment;
    }

    public void setSysDepartment(Department sysDepartment) {
        this.sysDepartment = sysDepartment;
    }

    public Position getSysPosition() {
        return this.sysPosition;
    }

    public void setSysPosition(Position sysPosition) {
        this.sysPosition = sysPosition;
    }

}
