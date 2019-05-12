package com.swain.core.common.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * `用户类型枚举
 */

public enum UserPermissionEnum {

    ADMIN(0,"管理员"),
    STAFF(1,"员工");


    @Getter
    private Integer code;
    @Getter
    private String desc;

    UserPermissionEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDesc(Integer code){
        return Arrays.stream(values())
                .filter(e -> e.getCode().equals(code))
                .map(UserPermissionEnum::getDesc)
                .findFirst()
                .orElse("");
    }


}
