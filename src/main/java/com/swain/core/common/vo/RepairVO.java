package com.swain.core.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class RepairVO implements Serializable {
    private Long repairId;
    /**维修的机器名称*/
    private String repairMachineName;
    /**维修的机器状态*/
    private Integer repairMachineType;
    /**维修价格*/
    private BigDecimal repairPrice;
    /**故障描述*/
    private String repairComment;
    /**逻辑删除位*/
    private Integer isDelete;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModified;

}
