package com.example.conndb.repository;

import com.example.conndb.entity.OrderGoods;
import com.example.conndb.entity.OrderGoodsPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderGoodsRepository extends JpaRepository<OrderGoods, OrderGoodsPk> {
}
