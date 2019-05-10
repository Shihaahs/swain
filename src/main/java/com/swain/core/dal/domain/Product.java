package com.swain.core.dal.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.swain.core.common.base.BaseModel;
import com.baomidou.mybatisplus.annotations.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 主键，自增长，生产数据id
     */
    @TableId(value = "product_id", type = IdType.AUTO)
    private Long productId;
    /**
     * 生产员工id
     */
    @TableField("product_user_id")
    private Long productUserId;
    /**
     * 生产机器id
     */
    @TableField("product_machine_id")
    private Long productMachineId;
    /**
     * 生产原料id
     */
    @TableField("product_material_id")
    private Long productMaterialId;
    /**
     * 生产原料重量
     */
    @TableField("product_material_weight")
    private BigDecimal productMaterialWeight;
    /**
     * 产出物名称
     */
    @TableField("product_out_name")
    private String productOutName;
    /**
     * 产出物重量
     */
    @TableField("product_out_weight")
    private BigDecimal productOutWeight;
    /**
     * 逻辑删除，0-存在，1-已被删除
     */
    @TableLogic
    @TableField("is_delete")
    private Integer isDelete;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("gmt_create")
    private Date gmtCreate;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("gmt_modified")
    private Date gmtModified;

}
