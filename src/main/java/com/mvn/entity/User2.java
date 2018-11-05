package com.mvn.entity;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("serial")
public class User2 implements Serializable{//用户实体类
    
    private String userName;
    
    private MultipartFile headimage;//上传文件会自动绑定到该属性

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public MultipartFile getHeadimage() {
		return headimage;
	}

	public void setHeadimage(MultipartFile headimage) {
		this.headimage = headimage;
	}
  
    
}
