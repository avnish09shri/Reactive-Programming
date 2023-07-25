package com.reactivemicroservice.productservice.util;

import com.reactivemicroservice.productservice.dto.ProductDTO;
import com.reactivemicroservice.productservice.entity.Product;
import org.springframework.beans.BeanUtils;

public class EntityDTOUtil {

    public static ProductDTO toDto(Product product){
        ProductDTO dto=new ProductDTO();
        dto.setDescription(product.getDescription());
        BeanUtils.copyProperties(product,dto);
        return dto;

    }

    public static Product toEntity(ProductDTO productDTO){
        Product product=new Product();
        product.setDescription(productDTO.getDescription());
        BeanUtils.copyProperties(productDTO,product);
        return product;

    }
}
