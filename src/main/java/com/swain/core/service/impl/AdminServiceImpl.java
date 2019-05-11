package com.swain.core.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.swain.core.dal.domain.User;
import com.swain.core.dal.manager.UserManager;
import com.swain.core.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserManager userManager;

    @Override
    public List<User> getAllUsers() {
        //获取全部用户信息，按照时间逆序显示
        return userManager.selectList(new EntityWrapper<User>()
                .orderDesc(Collections.singleton("gmt_modified")));
    }

    @Override
    public User getUserById(Long id) {
        return userManager.selectById(id);
    }

    @Override
    public Integer deleteUserById(Long id) {
        return userManager.deleteById(id);
    }

    @Override
    public Integer updateUser(User user) {
        return userManager.updateById(user);
    }

    @Override
    public Integer addUser(User user) {
        return userManager.insert(user);
    }


}
