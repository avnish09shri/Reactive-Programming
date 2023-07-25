package com.reactivemicroservice.productservice.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductDTO {

    private String id;
    private String description;
    private Integer price;
}
