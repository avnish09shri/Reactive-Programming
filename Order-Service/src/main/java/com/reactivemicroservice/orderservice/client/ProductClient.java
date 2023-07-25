package com.reactivemicroservice.orderservice.client;

import com.reactivemicroservice.orderservice.dto.ProductDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductClient {

    private WebClient webClient;

    public ProductClient(@Value("${product.service.url}") String url) {
        this.webClient = WebClient.builder().build();
    }

    public Mono<ProductDto> getProductById(String productId) {

        Mono<ProductDto> getProduct =
                this.webClient
                        .get()
                        .uri("{id}", productId)
                        .retrieve()
                        .bodyToMono(ProductDto.class);
        return getProduct;

    }

    public Flux<ProductDto> getAllProducts(){
        Flux<ProductDto> getAllProducts =
                this.webClient
                        .get()
                        .uri("/")
                        .retrieve()
                        .bodyToFlux(ProductDto.class);
        return getAllProducts;
    }
}
