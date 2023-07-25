package com.reactivemicroservice.orderservice.service;

import com.reactivemicroservice.orderservice.client.ProductClient;
import com.reactivemicroservice.orderservice.client.UserClient;
import com.reactivemicroservice.orderservice.dto.OrderRequestDto;
import com.reactivemicroservice.orderservice.dto.OrderResponseDto;
import com.reactivemicroservice.orderservice.dto.RequestContext;
import com.reactivemicroservice.orderservice.repository.OrderRepository;
import com.reactivemicroservice.orderservice.util.EntityDtoUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private ProductClient productClient;
    private UserClient userClient;

    public OrderService(OrderRepository orderRepository, ProductClient productClient, UserClient userClient) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
        this.userClient = userClient;
    }

    public Mono<OrderResponseDto> processOrder(Mono<OrderRequestDto> orderRequestDtoMono){
        return orderRequestDtoMono.map(RequestContext::new)
                .flatMap(this::productClientResponse)
                .flatMap(this::userClientResponse)
                .map(EntityDtoUtil::getPurchaseOrder)
                .map(this.orderRepository::save)
                .map(EntityDtoUtil::getOrderResponseDto)
                .subscribeOn(Schedulers.boundedElastic());
    }

    private Mono<RequestContext> productClientResponse(RequestContext requestContext){
        return this.productClient.getProductById(requestContext.getOrderRequestDto().getProductId())
                .doOnNext(requestContext::setProductDto)
                .retryWhen(Retry.fixedDelay(5, Duration.ofSeconds(1)))
                .thenReturn(requestContext);
    }

    private Mono<RequestContext> userClientResponse(RequestContext requestContext){
        return this.userClient.authorizeTransaction(requestContext.getTransactionRequestDto())
                .doOnNext(requestContext::setTransactionResponseDto).thenReturn(requestContext);
    }
}
