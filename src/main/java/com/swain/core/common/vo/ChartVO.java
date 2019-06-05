package com.swain.core.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ChartVO implements Serializable{


    private String chartName;

    private Double[] chartData;

}
