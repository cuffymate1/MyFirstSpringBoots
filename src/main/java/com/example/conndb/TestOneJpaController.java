package com.example.conndb;


import com.example.conndb.entity.Shop;
import com.example.conndb.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestOneJpaController {

    @Autowired
    private ShopRepository shopRepository;

    @GetMapping("test/select")
    public List<Shop> selectAll(){
        return  shopRepository.findAll();
    }

    @GetMapping("test/getid")
    public Shop getId(
            @RequestParam("id")String id
    ){
        return shopRepository.findById(id).orElseThrow();
    }


    @PostMapping("test/insert")
    public Shop insertData(
            @RequestBody Shop shop
    ){
        return shopRepository.save(shop);
    }

    @GetMapping("test/update")
    public Shop updateShop(
            @RequestBody Shop shop
    ){
        shopRepository.findById(shop.getShopId()).orElseThrow();
        return shopRepository.save(shop);
    }

    @GetMapping("test/delete")
    public void delete(
            @RequestParam("id")String id
    ){
        shopRepository.findById(id).orElseThrow();
        shopRepository.deleteById(id);
    }
}
