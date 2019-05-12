package com.swain.core.service.impl;


import com.swain.core.dal.domain.Material;
import com.swain.core.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StaffServiceImpl implements StaffService{


    @Override
    public Integer insertMaterial(Material material) {
        return null;
    }

    @Override
    public Integer deleteMaterialById(Long id) {
        return null;
    }

    @Override
    public Integer updateMaterial(Material material) {
        return null;
    }

    @Override
    public List<Material> getAllMaterials() {
        return null;
    }

    @Override
    public Material getMaterialById(Long id) {
        return null;
    }
}
