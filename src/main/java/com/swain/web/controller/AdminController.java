package com.swain.web.controller;


import com.swain.core.common.enums.ConstantEnum;
import com.swain.core.dal.domain.User;
import com.swain.core.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * RestController和Controller的区别
 * Controller的人会经过Spring的ViewResolevr去检索
 * 而RestController就直接把值返回给前端
 * <p>
 * RestController = Controller + ResponseBody
 */

@Slf4j
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * =======   登录管理   =======
     */
    @RequestMapping(value = "/login.json", method = RequestMethod.POST)
    public User login(User user) {
        //这里自己写吧，我直接返回一个值了

        return user;
    }


    /**
     * =======   管理员-用户管理   =======
     */

    @RequestMapping(value = "/admin/getAllUsers.json", method = RequestMethod.POST)
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    @RequestMapping(value = "/admin/addUser.json", method = RequestMethod.POST)
    public Integer addUser(@RequestBody User user) {
        return adminService.addUser(user);
    }

    @RequestMapping(value = "/admin/deleteUser.json", method = RequestMethod.POST)
    public Integer deleteUser(@RequestBody User user) {
        if (Objects.isNull(user.getUserId())) {
            log.error("管理员删除用户异常 -> id为空");
            return ConstantEnum.ZONE.getCode();
        }
        return adminService.deleteUserById(user.getUserId());
    }

    @RequestMapping(value = "/admin/updateUser.json", method = RequestMethod.POST)
    public Integer updateUser(@RequestBody User user) {
        if (Objects.isNull(user.getUserId())) {
            log.error("管理员修改用户异常 -> id为空");
            return ConstantEnum.ZONE.getCode();
        }
        return adminService.updateUser(user);
    }

    @RequestMapping(value = "/admin/getUserById.json", method = RequestMethod.POST)
    public User getUserById(@RequestBody User user) {
        if (Objects.isNull(user.getUserId())) {
            log.error("管理员根据id获取用户异常 -> id为空");
            return null;
        }
        return adminService.getUserById(user.getUserId());
    }




}
