package com.example.conndb.dto;


import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Accessors(chain = true)
@Data
public class OrderGoodsDto {

    private String orderId;
    private String goodsId;
    private Integer numberOfPiece;
    private BigDecimal priceOfPiece;
    private BigDecimal sum;
}
