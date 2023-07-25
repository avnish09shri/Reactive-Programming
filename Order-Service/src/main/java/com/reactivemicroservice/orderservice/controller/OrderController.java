package com.reactivemicroservice.orderservice.controller;

import com.reactivemicroservice.orderservice.dto.OrderRequestDto;
import com.reactivemicroservice.orderservice.dto.OrderResponseDto;
import com.reactivemicroservice.orderservice.service.OrderQueryService;
import com.reactivemicroservice.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderQueryService orderQueryService;

    @PostMapping
    public Mono<ResponseEntity<OrderResponseDto>> order(@RequestBody Mono<OrderRequestDto> orderRequestDto){
        return this.orderService.processOrder(orderRequestDto)
                .map(ResponseEntity::ok)
                .onErrorReturn(WebClientResponseException.class, ResponseEntity.badRequest().build())
                .onErrorReturn(WebClientResponseException.class, ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build());
    }

    @GetMapping("/user/{userId}")
    public Flux<OrderResponseDto> getOrderByUserId(@PathVariable int id){

        return this.orderQueryService.getProductsByUserId(id);
    }
}
