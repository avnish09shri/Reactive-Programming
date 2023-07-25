package com.reactivemicroservice.productservice.service;

import com.reactivemicroservice.productservice.dto.ProductDTO;
import com.reactivemicroservice.productservice.repository.ProductRepository;
import com.reactivemicroservice.productservice.util.EntityDTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Flux<ProductDTO> getAll(){
        return this.productRepository.findAll()
                .map(EntityDTOUtil::toDto);
    }

    public Flux<ProductDTO> getProductByPriceRange(int min, int max){
        return this.productRepository.findByPriceBetween(Range.closed(min, max))
                .map(EntityDTOUtil::toDto);
    }

    public Mono<ProductDTO> getProductById(String id){
        return this.productRepository.findById(id)
                .map(EntityDTOUtil::toDto);

    }

    public Mono<ProductDTO> insertProduct(Mono<ProductDTO> productDtoMono){
        return productDtoMono
                .map(EntityDTOUtil::toEntity)
                .flatMap(this.productRepository::insert)
                .map(EntityDTOUtil::toDto);
    }

    public Mono<ProductDTO> updateProduct(String id, Mono<ProductDTO> productDtoMono){
        return this.productRepository.findById(id)
                .flatMap(p -> productDtoMono
                        .map(EntityDTOUtil::toEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(this.productRepository::save)
                .map(EntityDTOUtil::toDto);
    }

    public Mono<Void> deleteProduct(String id){
        return this.productRepository.deleteById(id);
    }
}
