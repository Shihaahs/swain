package com.swain.core.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductVO implements Serializable {
    private Long productId;
    /**
     * 产品员工名称
     */

    private String productUserName;
    /**
     * 产品机器名称
     */
    private String productMachineName;

    /**
     * 投入物料名称
     */
    private String productMaterialName;

    /**
     * 投入物料重量
     */
    private BigDecimal productMaterialWeight;



    private String productOutName;

    private BigDecimal productOutWeight;
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
