package com.mvn.entity;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("serial")
public class User2 implements Serializable{//�û�ʵ����
    
    private String userName;
    
    private MultipartFile headimage;//�ϴ��ļ����Զ��󶨵�������

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
