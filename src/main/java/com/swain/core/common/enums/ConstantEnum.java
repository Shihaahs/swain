package com.swain.core.common.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * 常量枚举
 */

public enum ConstantEnum {

    ZONE(0,"0"),
    ONE(1,"1");


    @Getter
    private Integer code;
    @Getter
    private String desc;

    ConstantEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDesc(Integer code){
        return Arrays.stream(values())
                .filter(e -> e.getCode().equals(code))
                .map(ConstantEnum::getDesc)
                .findFirst()
                .orElse("");
    }


}
