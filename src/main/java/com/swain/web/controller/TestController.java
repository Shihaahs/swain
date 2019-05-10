package com.swain.web.controller;


import com.swain.core.dal.domain.User;
import com.swain.core.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    private AdminService adminService;


    @RequestMapping("/test/test1.json")
    @ResponseBody
    public List<User> testTest1() {
        return adminService.getAllUser();
    }
}
