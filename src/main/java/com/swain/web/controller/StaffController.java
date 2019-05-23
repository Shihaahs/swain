package com.swain.web.controller;


import com.swain.core.common.enums.ConstantEnum;
import com.swain.core.common.vo.RepairVO;
import com.swain.core.dal.domain.Material;
import com.swain.core.dal.domain.Product;
import com.swain.core.dal.domain.Repair;
import com.swain.core.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
public class StaffController {

    @Autowired
    private StaffService staffService;
    /**
     * =======   员工-物料管理   =======
     */
    @RequestMapping(value = "/staff/getAllMaterials.json", method = RequestMethod.POST)
    public List<Material> getAllMaterials() {
        return staffService.getAllMaterials();
    }

    @RequestMapping(value = "/staff/addMaterial.json", method = RequestMethod.POST)
    public Integer addMaterial(@RequestBody Material material) {
        return staffService.addMaterial(material);
    }

    @RequestMapping(value = "/staff/deleteMaterial.json", method = RequestMethod.POST)
    public Integer deleteMaterial(@RequestBody Material material) {
        if (Objects.isNull(material.getMaterialId())) {
            log.error("员工删除物料异常 -> id为空");
            return ConstantEnum.ZONE.getCode();
        }
        return staffService.deleteMaterialById(material.getMaterialId());
    }

    @RequestMapping(value = "/staff/updateMaterial.json", method = RequestMethod.POST)
    public Integer updateMaterial(@RequestBody Material material) {
        if (Objects.isNull(material.getMaterialId())) {
            log.error("员工修改物料异常 -> id为空");
            return ConstantEnum.ZONE.getCode();
        }
        return staffService.updateMaterial(material);
    }

    @RequestMapping(value = "/staff/getMaterialById.json", method = RequestMethod.POST)
    public Material getMaterialById(@RequestBody Material material) {
        if (Objects.isNull(material.getMaterialId())) {
            log.error("员工根据id获取物料异常 -> id为空");
            return null;
        }
        return staffService.getMaterialById(material.getMaterialId());
    }
    /**员工 生产数据录入*/
    @RequestMapping(value = "/staff/addProduct.json", method = RequestMethod.POST)
    public Integer addProduct(@RequestBody Product product) {
        return staffService.addProduct(product);
    }

    /** 员工 故障上报*/
    @RequestMapping(value = "/staff/addRepair.json", method = RequestMethod.POST)
    public Integer addRepair(@RequestBody Repair repair) {
        return staffService.addRepair(repair);
    }

    @RequestMapping(value = "/staff/getAllRepairs.json", method = RequestMethod.POST)
    public List<RepairVO> getAllRepairs() {
        return staffService.getAllRepairs();
    }
}
