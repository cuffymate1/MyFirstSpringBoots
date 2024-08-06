package com.example.conndb;


import com.example.conndb.dto.ShopDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConnDbController {

    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(path = "conndb", method = {
            RequestMethod.GET,
            RequestMethod.POST
    })
    public String testConn() {
           List<Tuple> resultFromDb =entityManager.createNativeQuery("SELECT * FROM shop", Tuple.class).getResultList();

           for(Tuple tuple: resultFromDb){
               String shopId = tuple.get("shop_id", String.class);
               String shopName = tuple.get("shop_name", String.class);
               System.out.println(shopId);
               System.out.println(shopName);
           }
           return resultFromDb.toString();
    }


    @GetMapping("getbyId")
    public ShopDto getById(
            @RequestParam("id")String id
    ){
        List<Tuple> resultFromDb = entityManager.createNativeQuery("SELECT * FROM shop WHERE shop_id = :shopId", Tuple.class)
                .setParameter("shopId", id)
                .getResultList();

        if(resultFromDb.isEmpty()){
            return null;
        }else {
            Tuple tuple = resultFromDb.get(0);
            ShopDto shopDto = new ShopDto();
            shopDto.setShopId(tuple.get("shop_id", String.class));
            shopDto.setShopName(tuple.get("shop_name", String.class));
            return  shopDto;
        }
    }

    @Transactional
    @PostMapping("insert")
    public boolean insertData(
            @RequestBody ShopDto shopDto
    ){
        int rowEffect = entityManager.createNativeQuery("INSERT INTO shop ('shop_id','shop_name') VALUES ( :shopId, :shopName)")
                .setParameter("shopId", shopDto.getShopId())
                .setParameter("shopName", shopDto.getShopName())
                .executeUpdate();
        return rowEffect > 0;
    }

    @Transactional
    @PostMapping("update")
    public boolean updateData(
            @RequestBody ShopDto shopDto
    ){
            int rowEffect = entityManager.createNativeQuery("UPDATE shop SET shop_id = :shopId, shop_name = :shopName WHERE shop_id = :shopId")
                    .setParameter("shopId", shopDto.getShopId())
                    .setParameter("shopName", shopDto.getShopName())
                    .executeUpdate();
            return rowEffect > 0;
    }

    @Transactional
    @GetMapping("delete")
    public boolean deleteData(
            @RequestParam("id") String id
    ){
        int rowEffect = entityManager.createNativeQuery("DELETE FROM shop WHERE shop_id = :shopId")
                .setParameter("shopId", id)
                .executeUpdate();
        return rowEffect > 0;
    }
}
