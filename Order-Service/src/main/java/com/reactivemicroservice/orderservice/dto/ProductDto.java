package com.reactivemicroservice.orderservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ProductDto {

    private String productId;
    private String description;
    private Integer price;

    public ProductDto(String description, Integer price) {
        this.description = description;
        this.price = price;
    }
}
