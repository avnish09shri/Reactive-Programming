package com.reactivemicroservice.orderservice.entity;

import com.reactivemicroservice.orderservice.dto.OrderStatus;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Data
@ToString
@Entity
public class Order {

    @Id
    @GeneratedValue
    private Integer id;
    private String productId;
    private Integer userId;
    private Integer amount;
    private OrderStatus status;
}
