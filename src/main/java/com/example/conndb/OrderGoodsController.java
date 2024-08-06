package com.example.conndb;

import com.example.conndb.dto.GetOrderGoodsDto;
import com.example.conndb.dto.InsertOrderGoods;
import com.example.conndb.dto.OrderGoodsDto;
import com.example.conndb.entity.OrderGoods;
import com.example.conndb.entity.OrderGoodsPk;
import com.example.conndb.repository.OrderGoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderGoodsController {

    @Autowired
    private OrderGoodsRepository orderGoodsRepository;

    @PostMapping("order/get")
    public OrderGoodsDto getOrder(
            @RequestBody GetOrderGoodsDto getOrderGoodsDto
    ) {
        OrderGoods orderGoods = orderGoodsRepository.findById(
                        new OrderGoodsPk()
                                .setGoodsId(getOrderGoodsDto.getGoodsId())
                                .setOrderId(getOrderGoodsDto.getOrderId()))
                .orElseThrow();

        return new OrderGoodsDto()
                .setGoodsId(orderGoods.getId().getGoodsId())
                .setOrderId(orderGoods.getId().getOrderId())
                .setNumberOfPiece(orderGoods.getNumberOfPiece())
                .setPriceOfPiece(orderGoods.getPricePerPiece())
                .setSum(orderGoods.getSum());
    }

    @PostMapping("order/insert")
    public OrderGoodsDto insertOrder(
            @RequestBody InsertOrderGoods insertOrderGoods
    ) {
        OrderGoods orderGoods = new OrderGoods()
                .setId(new OrderGoodsPk()
                        .setOrderId(insertOrderGoods.getOrderId())
                        .setGoodsId(insertOrderGoods.getGoodsId())
                )
                .setSum(insertOrderGoods.getSum())
                .setNumberOfPiece(insertOrderGoods.getNumberOfPiece())
                .setPricePerPiece(insertOrderGoods.getPricePerPiece());
        orderGoods = orderGoodsRepository.save(orderGoods);

        return new OrderGoodsDto()
                .setGoodsId(orderGoods.getId().getGoodsId())
                .setOrderId(orderGoods.getId().getOrderId())
                .setNumberOfPiece(orderGoods.getNumberOfPiece())
                .setPriceOfPiece(orderGoods.getPricePerPiece())
                .setSum(orderGoods.getSum());
    }
}
