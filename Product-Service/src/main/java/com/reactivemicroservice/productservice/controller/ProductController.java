package com.reactivemicroservice.productservice.controller;

import com.reactivemicroservice.productservice.dto.ProductDTO;
import com.reactivemicroservice.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/")
    public Flux<ProductDTO> all(){
        return this.service.getAll();
    }

    @GetMapping("/price-range")
    public Flux<ProductDTO> getByPriceRange(@RequestParam("min") int min,
                                            @RequestParam("max") int max){
        return this.service.getProductByPriceRange(min, max);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ProductDTO>> getProductById(@PathVariable String id){
        //this.simulateRandomException();
        return this.service.getProductById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ProductDTO> insertProduct(@RequestBody Mono<ProductDTO> productDtoMono){
        return this.service.insertProduct(productDtoMono);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ProductDTO>> updateProduct(@PathVariable String id, @RequestBody Mono<ProductDTO> productDtoMono){
        return this.service.updateProduct(id, productDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id){
        return this.service.deleteProduct(id);
    }
}
