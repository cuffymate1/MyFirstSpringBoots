package com.example.conndb.dto;


import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class GetOrderGoodsDto {

    private String orderId;
    private String goodsId;
}
