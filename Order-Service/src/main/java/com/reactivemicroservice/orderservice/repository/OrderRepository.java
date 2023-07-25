package com.reactivemicroservice.orderservice.repository;

import com.reactivemicroservice.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.core.publisher.Flux;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Flux<Order> findByUserId(int userId);
}
