package com.reactivemicroservice.orderservice.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderResponseDto {

    private Integer orderId;
    private Integer userId;
    private String productId;
    private Integer amount;
    private OrderStatus status;
}
