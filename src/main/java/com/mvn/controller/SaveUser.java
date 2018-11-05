package com.mvn.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvn.entity.User;
import com.mvn.service.UserService;

@SuppressWarnings("restriction")
@Controller
public class SaveUser {

    @Resource
    UserService userService;
    
	@RequestMapping(value="/saveUser")
    public String saveUser(User user,Model model){
    	model.addAttribute("message", "value of Model returning");
        userService.save(user);
        return "saveSuccess";
    }
}
