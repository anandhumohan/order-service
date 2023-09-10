package com.sci.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sci.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
