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
public class Machine extends BaseModel {

    private static final long serialVersionUID = 1L;
    /**
     * 主键，自增长，机器id
     */
    @TableId(value = "machine_id", type = IdType.AUTO)
    private Long machineId;
    /**
     * 机器名称
     */
    @TableField("machine_name")
    private String machineName;
    /**
     * 机器类型，0-损坏，1-正常
     */
    @TableField("machine_type")
    private Integer machineType;
    /**
     * 机器员工id
     */
    @TableField("machine_user_id")
    private Long machineUserId;
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
