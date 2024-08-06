package com.example.conndb;


import com.example.conndb.dto.GetOrderGoodsDto;
import com.example.conndb.dto.InsertOrderGoods;
import com.example.conndb.dto.OrderGoodsDto;
import com.example.conndb.entity.Goods;
import com.example.conndb.entity.Order;
import com.example.conndb.entity.OrderGoodsFk;
import com.example.conndb.entity.OrderGoodsPk;
import com.example.conndb.repository.OrderGoodsFkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderGoodsFkController {

    @Autowired
    private OrderGoodsFkRepository orderGoodsFkRepository;

    @PostMapping("orders/get")
    public OrderGoodsDto getOrderFk(
            @RequestBody GetOrderGoodsDto getOrderGoodsDto
    ) {
        OrderGoodsFk orderGoodsFk = orderGoodsFkRepository.findById(
                new OrderGoodsPk()
                        .setGoodsId(getOrderGoodsDto.getGoodsId())
                        .setOrderId(getOrderGoodsDto.getOrderId())
        ).orElseThrow();

        return new OrderGoodsDto()
                .setGoodsId(orderGoodsFk.getId().getGoodsId())
                .setOrderId(orderGoodsFk.getId().getOrderId())
                .setNumberOfPiece(orderGoodsFk.getNumberOfPiece())
                .setPriceOfPiece(orderGoodsFk.getPricePerPiece())
                .setSum(orderGoodsFk.getSum());
    }

    @PostMapping("orders/insert")
    public OrderGoodsDto insert(
            @RequestBody InsertOrderGoods insertOrderGoodsDto
    ) {
        OrderGoodsFk orderGoodsFk = new OrderGoodsFk()
                .setId(new OrderGoodsPk())
                .setGoods(new Goods().setGoodsId(insertOrderGoodsDto.getGoodsId()))
                .setOrder(new Order().setOrderId(insertOrderGoodsDto.getOrderId()))
                .setSum(insertOrderGoodsDto.getSum())
                .setNumberOfPiece(insertOrderGoodsDto.getNumberOfPiece())
                .setPricePerPiece(insertOrderGoodsDto.getPricePerPiece());
        orderGoodsFk = orderGoodsFkRepository.save(orderGoodsFk);

        return new OrderGoodsDto()
                .setGoodsId(orderGoodsFk.getId().getGoodsId())
                .setOrderId(orderGoodsFk.getId().getOrderId())
                .setNumberOfPiece(orderGoodsFk.getNumberOfPiece())
                .setSum(orderGoodsFk.getSum())
                .setPriceOfPiece(orderGoodsFk.getPricePerPiece());
    }
}
