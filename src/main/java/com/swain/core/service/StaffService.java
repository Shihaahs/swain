package com.swain.core.service;

import com.swain.core.dal.domain.Material;

import java.util.List;

public interface StaffService {
    /**
     * 添加物料
     * @param material
     * @return
     */
    Integer insertMaterial(Material material);

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

}
