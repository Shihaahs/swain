package com.swain.web.controller;


import com.swain.core.common.enums.ConstantEnum;
import com.swain.core.common.util.DateUtil;
import com.swain.core.common.vo.MachineVO;
import com.swain.core.common.vo.ProductVO;
import com.swain.core.common.vo.RepairVO;
import com.swain.core.dal.domain.*;
import com.swain.core.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Date;
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
    public User login(@RequestBody User user) {

        return adminService.checkLogin(user);
    }
    @RequestMapping(value = "/register.json", method = RequestMethod.POST)
    public Integer register(@RequestBody User user) {

        return adminService.register(user);
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

    @RequestMapping(value = "/admin/getAllStaff.json", method = RequestMethod.POST)
    public List<User> getAllStaff() {
        return adminService.getAllStaff();
    }



    /**
     * =======   管理员-机器管理   =======
     */
    @RequestMapping(value = "/admin/getAllMachines.json", method = RequestMethod.POST)
    public List<MachineVO> getAllMachines() {
        return adminService.getAllMachines();
    }

    @RequestMapping(value = "/admin/addMachine.json", method = RequestMethod.POST)
    public Integer addUser(@RequestBody Machine machine) {
        return adminService.addMachine(machine);
    }

    @RequestMapping(value = "/admin/deleteMachine.json", method = RequestMethod.POST)
    public Integer deleteMachine(@RequestBody Machine machine) {
        if (Objects.isNull(machine.getMachineId())) {
            log.error("管理员删除机器异常 -> id为空");
            return ConstantEnum.ZONE.getCode();
        }
        return adminService.deleteMachineById(machine.getMachineId());
    }

    @RequestMapping(value = "/admin/updateMachine.json", method = RequestMethod.POST)
    public Integer updateMachine(@RequestBody Machine machine) {
        if (Objects.isNull(machine.getMachineId())) {
            log.error("管理员修改机器异常 -> id为空");
            return ConstantEnum.ZONE.getCode();
        }
        return adminService.updateMachine(machine);
    }

    @RequestMapping(value = "/admin/getMachineById.json", method = RequestMethod.POST)
    public Machine getMachineById(@RequestBody Machine machine) {
        if (Objects.isNull(machine.getMachineId())) {
            log.error("管理员根据id获取机器异常 -> id为空");
            return null;
        }
        return adminService.getMachineById(machine.getMachineId());
    }

    @RequestMapping(value = "/admin/getMachineByUser.json", method = RequestMethod.POST)
    public List<Machine> getMachineByUser(@RequestBody User user) {
        if (Objects.isNull(user.getUserId())) {
            log.error("管理员根据用户获取机器异常 -> id为空");
            return null;
        }
        return adminService.getMachineByUser(user);
    }


    /**
     * =======   管理员-物料管理   =======
     */
    @RequestMapping(value = "/admin/getAllMaterials.json", method = RequestMethod.POST)
    public List<Material> getAllMaterials() {
        return adminService.getAllMaterials();
    }

    @RequestMapping(value = "/admin/addMaterial.json", method = RequestMethod.POST)
    public Integer addMaterial(@RequestBody Material material) {
        return adminService.addMaterial(material);
    }

    @RequestMapping(value = "/admin/deleteMaterial.json", method = RequestMethod.POST)
    public Integer deleteMaterial(@RequestBody Material material) {
        if (Objects.isNull(material.getMaterialId())) {
            log.error("管理员删除物料异常 -> id为空");
            return ConstantEnum.ZONE.getCode();
        }
        return adminService.deleteMaterialById(material.getMaterialId());
    }

    @RequestMapping(value = "/admin/updateMaterial.json", method = RequestMethod.POST)
    public Integer updateMaterial(@RequestBody Material material) {
        if (Objects.isNull(material.getMaterialId())) {
            log.error("管理员修改物料异常 -> id为空");
            return ConstantEnum.ZONE.getCode();
        }
        return adminService.updateMaterial(material);
    }

    @RequestMapping(value = "/admin/getMaterialById.json", method = RequestMethod.POST)
    public Material getMaterialById(@RequestBody Material material) {
        if (Objects.isNull(material.getMaterialId())) {
            log.error("管理员根据id获取物料异常 -> id为空");
            return null;
        }
        return adminService.getMaterialById(material.getMaterialId());
    }





    /**
     * =======   管理员-产品管理   =======
     */
    @RequestMapping(value = "/admin/getAllProducts.json", method = RequestMethod.POST)
    public List<ProductVO> getAllProducts() {
        return adminService.getAllProducts();
    }

//    @RequestMapping(value = "/admin/addProduct.json", method = RequestMethod.POST)
//    public Integer addMaterial(@RequestBody Material material) {
//        return adminService.addMaterial(material);
//    }

    @RequestMapping(value = "/admin/deleteProduct.json", method = RequestMethod.POST)
    public Integer deleteProduct(@RequestBody Product product) {
        if (Objects.isNull(product.getProductId())) {
            log.error("管理员删除产品异常 -> id为空");
            return ConstantEnum.ZONE.getCode();
        }
        return adminService.deleteProductById(product.getProductId());
    }

    @RequestMapping(value = "/admin/updateProduct.json", method = RequestMethod.POST)
    public Integer updateProduct(@RequestBody Product product) {
        if (Objects.isNull(product.getProductId())) {
            log.error("管理员修改产品异常 -> id为空");
            return ConstantEnum.ZONE.getCode();
        }
        return adminService.updateProduct(product);
    }

    @RequestMapping(value = "/admin/getProductById.json", method = RequestMethod.POST)
    public Product getProductById(@RequestBody Product product) {
        if (Objects.isNull(product.getProductId())) {
            log.error("管理员根据id获取物料异常 -> id为空");
            return null;
        }
        return adminService.getProductById(product.getProductId());
    }


    /**
     * =======   管理员-维修管理   =======
     */
    @RequestMapping(value = "/admin/getAllRepairs.json", method = RequestMethod.POST)
    public List<RepairVO> getAllRepairs() {
        return adminService.getAllRepairs();
    }

//    @RequestMapping(value = "/admin/addProduct.json", method = RequestMethod.POST)
//    public Integer addMaterial(@RequestBody Material material) {
//        return adminService.addMaterial(material);
//    }

    @RequestMapping(value = "/admin/deleteRepair.json", method = RequestMethod.POST)
    public Integer deleteRepair(@RequestBody Repair repair) {
        if (Objects.isNull(repair.getRepairId())) {
            log.error("管理员删除产品异常 -> id为空");
            return ConstantEnum.ZONE.getCode();
        }
        return adminService.deleteRepairById(repair.getRepairId());
    }

    @RequestMapping(value = "/admin/updateRepair.json", method = RequestMethod.POST)
    public Integer updateRepair(@RequestBody Repair repair) {
        if (Objects.isNull(repair.getRepairId())) {
            log.error("管理员修改维修信息异常 -> id为空");
            return ConstantEnum.ZONE.getCode();
        }
        return adminService.updateRepair(repair);
    }

    @RequestMapping(value = "/admin/getRepairById.json", method = RequestMethod.POST)
    public RepairVO getRepairById(@RequestBody Repair repair) {
        if (Objects.isNull(repair.getRepairId())) {
            log.error("管理员根据id获取维修信息异常 -> id为空");
            return null;
        }
        return adminService.getRepairById(repair.getRepairId());
    }
    @RequestMapping(value = "/admin/getBusinessReport.json", method = RequestMethod.GET)
    public Boolean getBusinessReport(HttpServletResponse response) throws Exception{

        XSSFWorkbook workbook = adminService.getBusinessReport();

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + new String("订单报表".getBytes("UTF-8"),"iso-8859-1")
                + DateUtil.parseToString(new Date(),DateUtil.NORMAL_PATTERN) + ".xls");
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        return null;
    }
}
