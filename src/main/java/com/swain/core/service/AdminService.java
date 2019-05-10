package com.swain.core.service;

import com.swain.core.dal.domain.User;

import java.util.List;

public interface AdminService {

    /**
     * 获取全部用户信息
     */
    List<User> getAllUser();
}
