 package com.springboot.test.model.vo;

 public class ResultVO {
     
    private Integer code;
    private String msg;
    private Object data;
    
    
    public ResultVO() {
        super();
    }
    
    public ResultVO(Integer code, String msg, Object data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
     
}
