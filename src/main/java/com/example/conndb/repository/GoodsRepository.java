package com.example.conndb.repository;

import com.example.conndb.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, String> {

    List<Goods> findByShopShopId(String shopId);
}
