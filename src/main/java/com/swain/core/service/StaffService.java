package com.swain.core.service;

import com.swain.core.common.vo.ProductVO;
import com.swain.core.common.vo.RepairVO;
import com.swain.core.dal.domain.Material;
import com.swain.core.dal.domain.Product;
import com.swain.core.dal.domain.Repair;
import com.swain.core.dal.domain.User;

import java.util.List;

public interface StaffService {
    Integer addMaterial(Material material);

    /**
     * 删除物料
     * @param id
     * @return
     */
    Integer deleteMaterialById(Long id);

    /**
     * 修改物料
     * @param material
     * @return
     */
    Integer updateMaterial(Material material);

    /**
     * 获取所有物料信息
     * @return
     */
    List<Material> getAllMaterials();


    /**
     * 依据获取物料信息
     * @return
     */
    Material getMaterialById(Long id);

    /**
     * 新增生产数据
     *
     */
    Integer addProduct(Product product);

    /***
     * 获取员工负责的生产数据
     */
    List<ProductVO> getAllProductsByUser(User user);

    Integer updateProduct(Product product);
    /**
     * 新增故障信息
     * @param repair
     * @return
     */
    Integer addRepair(Repair repair);

    List<RepairVO> getAllRepairs();

}
