package com.reactivemicroservice.orderservice.service;

import com.reactivemicroservice.orderservice.dto.OrderResponseDto;
import com.reactivemicroservice.orderservice.repository.OrderRepository;
import com.reactivemicroservice.orderservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
public class OrderQueryService {

    @Autowired
    private OrderRepository orderRepository;

    public Flux<OrderResponseDto> getProductsByUserId(int userId){
        return Flux.fromStream(() -> this.orderRepository.findByUserId(userId).toStream())
                .map(EntityDtoUtil::getOrderResponseDto)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
