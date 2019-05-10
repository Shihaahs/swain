package com.swain.core.service;

import com.swain.core.dal.domain.User;

import java.util.List;

public interface AdminService {

    /**
     * 获得全部用户注释
     * @return
     */
    List<User> getAllUsers();

    /**
     * 依据id获取用户信息
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 新增员工信息
     * @param user
     * @return
     */
    Integer insertUser(User user);

    /**
     * 依据id删除员工信息
     * @param id
     * @return
     */
    Integer deleteUserById(Long id);

    /**
     * 修改员工信息
     * @param user
     * @return
     */
    Integer updateUser(User user);

    /**
     * 获得全部用户注释
     * @return
     */
    List<User> getAllUsers();

    /**
     * 依据id获取用户信息
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 新增员工信息
     * @param user
     * @return
     */
    Integer insertUser(User user);

    /**
     * 依据id删除员工信息
     * @param id
     * @return
     */
    Integer deleteUserById(Long id);

    /**
     * 修改员工信息
     * @param user
     * @return
     */
    Integer updateUser(User user);







}
