package com.example.conndb;

import java.util.List;
import com.example.conndb.dto.GoodsDto;
import com.example.conndb.dto.InsertGoodsDto;
import com.example.conndb.entity.Goods;
import com.example.conndb.entity.Shop;
import com.example.conndb.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
public class GoodsController {


    @Autowired
    private GoodsRepository goodsRepository;

    @GetMapping("goods/{id}")
    public GoodsDto getGoods(
            @PathVariable("id")String id
    ){
        Goods goods = goodsRepository.findById(id).orElseThrow();


         return new GoodsDto().setGoodsId(goods.getGoodsId())
                 .setGoodsName(goods.getGoodsName())
                 .setGoodsPrice(goods.getGoodsPrice())
                 .setShopId(goods.getShop().getShopId())
                 .setShopName(goods.getShop().getShopName());
    }

    @PostMapping("goods/insert")
    public GoodsDto insertGoods(
            @RequestBody InsertGoodsDto insertGoodsDto
    ){
        Goods goods = new Goods()
                .setGoodsId(insertGoodsDto.getGoodsId())
                .setGoodsName(insertGoodsDto.getGoodsName())
                .setGoodsPrice(insertGoodsDto.getGoodsPrice())
                .setShop(new Shop().setShopId(insertGoodsDto.getShopId()));

        goods = goodsRepository.save(goods);

        return new GoodsDto().setGoodsId(goods.getGoodsId())
                .setGoodsName(goods.getGoodsName())
                .setGoodsPrice(goods.getGoodsPrice())
                .setShopId(goods.getShop().getShopId())
                .setShopName(goods.getShop().getShopName());
    }



    @GetMapping("goods/delete")
    public void deleteGoods(
            @RequestParam("id")String id
    ){
        goodsRepository.findById(id).orElseThrow();
        goodsRepository.deleteById(id);
    }


    @GetMapping("goods/search")
    public List<GoodsDto> searchData(
            @RequestParam("shopId")String shopId
    ){
        return goodsRepository.findByShopShopId(shopId).stream().map(goods -> {
            return new GoodsDto()
                    .setGoodsId(goods.getGoodsId())
                    .setGoodsName(goods.getGoodsName())
                    .setGoodsPrice(goods.getGoodsPrice())
                    .setShopId(goods.getShop().getShopId())
                    .setShopName(goods.getShop().getShopName());
        }).collect(Collectors.toList());
    }
}
