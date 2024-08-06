package com.example.conndb.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


@EqualsAndHashCode
@Accessors(chain = true)
@Data
@Embeddable
public class OrderGoodsPk implements Serializable {

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "goods_id")
    private String goodsId;
}
