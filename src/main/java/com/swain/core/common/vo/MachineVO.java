package com.swain.core.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MachineVO implements Serializable{

    private Long machineId;
    /**
     * 机器名称
     */

    private String machineName;
    /**
     * 机器类型，0-损坏，1-正常
     */
    private Integer machineType;
    /**
     * 机器员工姓名
     */

    private String machineUserName;
    /**
     * 逻辑删除，0-存在，1-已被删除
     */

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
