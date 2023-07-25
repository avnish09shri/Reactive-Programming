package com.reactivemicroservice.orderservice.util;

import com.reactivemicroservice.orderservice.dto.*;
import com.reactivemicroservice.orderservice.entity.Order;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static OrderResponseDto getOrderResponseDto(Order purchaseOrder){
        OrderResponseDto dto = new OrderResponseDto();
        BeanUtils.copyProperties(purchaseOrder, dto);
        dto.setOrderId(purchaseOrder.getId());
        return dto;
    }

    public static void setTransactionRequestDto(RequestContext requestContext){
        TransactionRequestDto dto = new TransactionRequestDto();
        dto.setUserId(requestContext.getOrderRequestDto().getUserId());
        dto.setAmount(requestContext.getProductDto().getPrice());
        requestContext.setTransactionRequestDto(dto);
    }

    public static Order getPurchaseOrder(RequestContext requestContext){
        Order purchaseOrder = new Order();
        purchaseOrder.setUserId(requestContext.getOrderRequestDto().getUserId());
        purchaseOrder.setProductId(requestContext.getOrderRequestDto().getProductId());
        purchaseOrder.setAmount(requestContext.getProductDto().getPrice());
        TransactionStatus status = requestContext.getTransactionResponseDto().getStatus();
        OrderStatus orderStatus = TransactionStatus.APPROVED.equals(status) ? OrderStatus.COMPLETED : OrderStatus.FAILED;
        purchaseOrder.setStatus(orderStatus);
        return purchaseOrder;
    }
}
