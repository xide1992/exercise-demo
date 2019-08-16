package com.exercise.demo.model.request;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/5/15 22:40
 */
public class AddOrderModel {

    @ApiModelProperty("产品id")
    private long productId;

    @ApiModelProperty("产品名称")
    private String productName;

    @ApiModelProperty("用户姓名")
    private long buyUserId;

    @ApiModelProperty("用户id")
    private String buyUserName;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getBuyUserId() {
        return buyUserId;
    }

    public void setBuyUserId(long buyUserId) {
        this.buyUserId = buyUserId;
    }

    public String getBuyUserName() {
        return buyUserName;
    }

    public void setBuyUserName(String buyUserName) {
        this.buyUserName = buyUserName;
    }
}
