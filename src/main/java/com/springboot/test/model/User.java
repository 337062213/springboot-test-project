package com.springboot.test.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="User",description="用户model")
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name = "id", value = "用户id", example="18f36b94852186b4ae998581a9140b51")
	private String id;

	@ApiModelProperty(name = "name", value = "用户姓名", example="张三")
	private String name;
	
	@ApiModelProperty(name = "password", value = "用户密码", example="admin")
	private String password;
	
	
	public User() {
        super();
    }
	

    public User(String id) {
        super();
        this.id = id;
    }


    public User(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public User(String id, String name, String password) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + "]";
	}
	

}
