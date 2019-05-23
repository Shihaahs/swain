package com.swain.core.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.swain.core.common.vo.RepairVO;
import com.swain.core.dal.domain.Machine;
import com.swain.core.dal.domain.Material;
import com.swain.core.dal.domain.Product;
import com.swain.core.dal.domain.Repair;
import com.swain.core.dal.manager.MachineManager;
import com.swain.core.dal.manager.MaterialManager;
import com.swain.core.dal.manager.ProductManager;
import com.swain.core.dal.manager.RepairManager;
import com.swain.core.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class StaffServiceImpl implements StaffService{

    /**
     * 物料的管理
     */
    @Autowired
    private MaterialManager materialManager;

    @Override
    public Integer addMaterial(Material material) {
        //若物料已存在，则增加库存即可
        Material m = materialManager.selectOne(new EntityWrapper<Material>().eq("material_name", material.getMaterialName()));
        if (m != null) {
            m.setMaterialTotal(m.getMaterialTotal().add(material.getMaterialTotal()));
            return materialManager.updateById(m);
        }
        return materialManager.insert(material);
    }

    @Override
    public Integer deleteMaterialById(Long id) {
        return materialManager.deleteById(id);
    }

    @Override
    public Integer updateMaterial(Material material) {
        return materialManager.updateById(material);
    }

    @Override
    public List<Material> getAllMaterials() {
        return materialManager.selectList(new EntityWrapper<Material>().orderDesc(Collections.singleton("gmt_modified"))
        );
    }

    @Override
    public Material getMaterialById(Long id) {
        return materialManager.selectById(id);
    }


    @Autowired
    private ProductManager productManager;
    @Override
    public Integer addProduct(Product product) {
        return productManager.insert(product);
    }


    @Autowired
    private RepairManager repairManager;

    @Autowired
    private MachineManager machineManager;
    @Override
    public Integer addRepair(Repair repair) {
        Machine repairMachine = machineManager.selectById(repair.getRepairMachineId());
        repairMachine.setMachineType(0);
        machineManager.updateById(repairMachine);
        return repairManager.insert(repair);
    }

    @Override
    public List<RepairVO> getAllRepairs() {
        List<Repair> repairList = repairManager.selectList(
                new EntityWrapper<Repair>().orderDesc(Collections.singleton("gmt_modified")));
        List<RepairVO> repairVoList = new ArrayList<>();
        Map<Long, String> repairMachineMap = new HashMap<>();
        for (Repair repair : repairList) {
            if (repairMachineMap.containsKey(repair.getRepairMachineId()) == false) {
                repairMachineMap.put(repair.getRepairMachineId(),
                        machineManager.selectById(repair.getRepairMachineId()).getMachineName());
            }
            RepairVO repairVO = new RepairVO();
            repairVO.setRepairId(repair.getRepairId());
            repairVO.setRepairMachineName(repairMachineMap.get(repair.getRepairMachineId()));
            repairVO.setRepairMachineType(repair.getRepairMachineType());
            repairVO.setRepairPrice(repair.getRepairPrice());
            repairVO.setRepairComment(repair.getRepairComment());
            repairVO.setGmtCreate(repair.getGmtCreate());
            repairVO.setGmtModified(repair.getGmtModified());

            repairVoList.add(repairVO);
        }
        return repairVoList;
    }

}
