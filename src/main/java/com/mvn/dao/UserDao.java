package com.mvn.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

import com.mvn.entity.User;

@SuppressWarnings("restriction")
@Repository
public class UserDao {

    @Resource
    SessionFactory sessionFactory;
    
    public void save(User user){
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }
}
