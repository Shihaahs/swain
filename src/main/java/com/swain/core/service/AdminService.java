package com.swain.core.service;

import com.swain.core.common.vo.MachineVO;
import com.swain.core.dal.domain.Machine;
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
    Integer addUser(User user);

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
     * 获取所有员工信息
     * @param
     * @return
     */
    List<User> getAllStaff();





    /**
     * 检查登陆
     * @param user
     * @return
     */
    User checkLogin(User user);

    /**
     * 获得所有机器
     * @return
     */
    List<MachineVO> getAllMachines();

    /**
     * 根据id获得machine
     * @param id
     * @return
     */
    MachineVO getMachineById(Long id);

    /**
     * 新增员工信息
     * @param machine
     * @return
     */
    Integer addMachine(Machine machine);

    /**
     * 依据id删除员工信息
     * @param id
     * @return
     */
    Integer deleteMachineById(Long id);

    /**
     * 修改员工信息
     * @param machine
     * @return
     */
    Integer updateMachine(Machine machine);


}
