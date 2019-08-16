package com.exercise.demo.model.request;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/5/15 16:49
 */
public class TestSelectModel {

    @ApiModelProperty(value = "主键", dataType = "long")
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
