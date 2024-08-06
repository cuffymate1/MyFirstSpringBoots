package com.example.conndb;

import com.example.conndb.entity.Shop;
import com.example.conndb.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpTimeoutException;
import java.util.List;

@RestController
public class TestJpaController {

    @Autowired
    private ShopRepository shopRepository;

    @GetMapping("jpa/select")
    public List<Shop> selectAll(){
      return shopRepository.findAll();
    }

    @GetMapping("jpa/getById")
    public Shop getById(
            @RequestParam("id")String id
    ){
        return  shopRepository.findById(id).orElseThrow();
    }


    @PostMapping("jpa/insert")
    public Shop insertData(
            @RequestBody Shop shop
    ){
       return shopRepository.save(shop);
    }

    @GetMapping("jpa/update")
    public Shop updateData(
            @RequestBody Shop shop
    ){
        shopRepository.findById(shop.getShopId()).orElseThrow();
        return shopRepository.save(shop);
    }

    @GetMapping("jpa/delete")
    public void deleteData(
            @RequestParam("id")String id
    ) {
            shopRepository.findById(id).orElseThrow();
            shopRepository.deleteById(id);
    }

    @GetMapping("jpa/findByShopName")
    public List<Shop> testFindByShopName(
            @RequestParam("shopName")String shopName
    ){
        return shopRepository.findByShopName(shopName);
    }
}
