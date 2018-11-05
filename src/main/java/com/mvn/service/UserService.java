package com.mvn.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvn.dao.UserDao;
import com.mvn.entity.User;

@SuppressWarnings("restriction")
@Service
public class UserService {
	
    @Resource
    UserDao userDao;

    @Transactional
    public void save(User user){
        userDao.save(user);
        User user2 = new User();
        user2.setName("ÀîËÄ");
        user2.setPassword("456");
        userDao.save(user2);
    }
    
}
