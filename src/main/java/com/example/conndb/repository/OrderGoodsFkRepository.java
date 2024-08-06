package com.example.conndb.repository;

import com.example.conndb.entity.OrderGoodsFk;
import com.example.conndb.entity.OrderGoodsPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderGoodsFkRepository extends JpaRepository<OrderGoodsFk, OrderGoodsPk> {
}
