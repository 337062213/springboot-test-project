 package com.springboot.test.model.vo;


public class DeptVO {
 
    private String id;
    private String deptName; 
    // 自己实现的一个参数，用来给前端传递加密字符串
    private String encryptJson;
    public DeptVO() {
        super();
    }
    public DeptVO(String id, String deptName, String encryptJson) {
        super();
        this.id = id;
        this.deptName = deptName;
        this.encryptJson = encryptJson;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public String getEncryptJson() {
        return encryptJson;
    }
    public void setEncryptJson(String encryptJson) {
        this.encryptJson = encryptJson;
    }
    
}
