package com.example.conndb.repository;

import com.example.conndb.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, String> {

    List<Shop> findByShopName(String shopName);
}
