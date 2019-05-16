package com.swain.core.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.swain.core.common.enums.UserPermissionEnum;
import com.swain.core.common.vo.MachineVO;
import com.swain.core.common.vo.ProductVO;
import com.swain.core.common.vo.RepairVO;
import com.swain.core.dal.domain.*;
import com.swain.core.dal.manager.*;
import com.swain.core.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    /**
     * 用户管理
     */
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
    public List<User> getAllStaff() {
        return userManager.selectList(
                new EntityWrapper<User>()
                        .eq("permission", UserPermissionEnum.STAFF.getCode()));
    }

    @Override
    public User checkLogin(User user) {
        User loginUser = userManager.selectOne(new EntityWrapper<User>()
                .eq("username", user.getUsername())
                .eq("password", user.getPassword()));
        if (loginUser == null) {
            return new User();
        }
        return loginUser;
    }

    @Override
    public Integer addUser(User user) {
        return userManager.insert(user);
    }


    /**
     * 机器管理
     */
    @Autowired
    private MachineManager machineManager;

    @Override
    public List<MachineVO> getAllMachines() {
        List<Machine> machineList = machineManager.selectList(new EntityWrapper<Machine>().orderDesc(Collections.singleton("gmt_modified")));
        List<MachineVO> machineVOList = new ArrayList<>();

        Map<Long, User> machineUserMap = new HashMap<>();

        for (Machine machine : machineList) {

            if (!machineUserMap.containsKey(machine.getMachineId())) {
                User machineUser = userManager.selectById(machine.getMachineUserId());
                machineUserMap.put(machine.getMachineId(), machineUser);
            }

            MachineVO machineVO = new MachineVO();
            machineVO.setMachineId(machine.getMachineId());
            machineVO.setMachineName(machine.getMachineName());
            machineVO.setMachineType(machine.getMachineType());
            machineVO.setMachineUserName(machineUserMap.get(machine.getMachineId()).getUsername());
            machineVO.setGmtCreate(machine.getGmtCreate());
            machineVO.setGmtModified(machine.getGmtModified());
            machineVOList.add(machineVO);
        }
        /**一个优化查询的解决方案*/
        /*List<Machine> machineList = machineManager.selectList(new EntityWrapper<Machine>().orderDesc(Collections.singleton("gmt_modified")));
        List<MachineVO> machineVOList = new ArrayList<>();

        //存放machineUserIds, 去重
        List<Long> machineUserIds = Lists.transform(machineList, Machine::getMachineUserId);

        Map<Long, User> userMap = userManager.selectList(new EntityWrapper<User>()
                .in("user_id", machineUserIds)).stream()
                .collect(Collectors.toMap(User::getUserId, x -> x));*/
        return machineVOList;
    }

    @Override
    public Machine getMachineById(Long id) {
        return machineManager.selectById(id);
    }

    @Override
    public Integer addMachine(Machine machine) {
        return machineManager.insert(machine);
    }

    @Override
    public Integer deleteMachineById(Long id) {
        return machineManager.deleteById(id);
    }

    @Override
    public Integer updateMachine(Machine machine) {
        return machineManager.updateById(machine);
    }


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


    /**
     * 生产数据管理
     */
    @Autowired
    private ProductManager productManager;

    @Override
    public List<ProductVO> getAllProducts() {
        List<Product> productList = productManager.selectList(new EntityWrapper<Product>().orderDesc(Collections.singleton("gmt_modified")));
        //List<MachineVO> machineVOList = new ArrayList<>();
        List<ProductVO> productVOList = new ArrayList<>();
        Map<Long, String> productUserNameMap = new HashMap<>();
        Map<Long, String> productMachineNameMap = new HashMap<>();
        Map<Long, String> productMaterialNameMap = new HashMap<>();
        for (Product product : productList) {
            if (productUserNameMap.containsKey(product.getProductUserId()) == false) {
                productUserNameMap.put(product.getProductUserId()
                        , userManager.selectById(product.getProductUserId()).getUsername());
            }
            if (productMachineNameMap.containsKey(product.getProductMachineId()) == false) {
                productMachineNameMap.put(product.getProductMachineId()
                        , machineManager.selectById(product.getProductMachineId()).getMachineName());
            }
            if (productMaterialNameMap.containsKey(product.getProductMaterialId()) == false) {
                productMaterialNameMap.put(product.getProductMaterialId()
                        , materialManager.selectById(product.getProductMaterialId()).getMaterialName());
            }
            ProductVO productVO = new ProductVO();

            productVO.setProductId(product.getProductId());
            productVO.setProductUserName(productUserNameMap.get(product.getProductUserId()));
            productVO.setProductMachineName(productMachineNameMap.get(product.getProductMachineId()));
            productVO.setProductMaterialName(productMaterialNameMap.get(product.getProductMaterialId()));
            productVO.setProductMaterialWeight(product.getProductMaterialWeight());
            productVO.setProductOutName(product.getProductOutName());
            productVO.setProductOutWeight(product.getProductOutWeight());
            productVO.setGmtCreate(product.getGmtCreate());
            productVO.setGmtModified(product.getGmtModified());

            productVOList.add(productVO);
        }

        return productVOList;
    }

    @Override
    public ProductVO getProductById(Long id) {
        return null;
    }

    @Override
    public Integer updateProduct(Product product) {
        return null;
    }

    @Override
    public Integer deleteProductById(Long id) {
        return productManager.deleteById(id);
    }


    @Autowired
    private RepairManager repairManager;

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

    @Override
    public RepairVO getRepairById(Long id) {
        Repair repair = repairManager.selectById(id);
        RepairVO repairVO = new RepairVO();
        repairVO.setRepairId(repair.getRepairId());
        repairVO.setRepairMachineName(machineManager.selectById(repair.getRepairMachineId())
                .getMachineName());
        repairVO.setRepairMachineType(repair.getRepairMachineType());
        repairVO.setRepairComment(repair.getRepairComment());
        repairVO.setRepairPrice(repair.getRepairPrice());
        repairVO.setGmtCreate(repair.getGmtCreate());
        repairVO.setGmtModified(repair.getGmtModified());
        return repairVO;
    }

    @Override
    public Integer updateRepair(Repair repair) {
        Machine machine = machineManager.selectById(
                repairManager.selectById(repair.getRepairId()).getRepairMachineId());
        machine.setMachineType(repair.getRepairMachineType());
        machineManager.updateById(machine);
        return repairManager.updateById(repair);
    }

    @Override
    public Integer deleteRepairById(Long id) {
        return repairManager.deleteById(id);
    }


}
