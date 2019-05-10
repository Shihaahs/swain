package com.swain.core.common.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
public abstract class BaseModel implements Serializable {
    private static final long serialVersionUID = -3467201945298812562L;

    public BaseModel() {
    }
}
