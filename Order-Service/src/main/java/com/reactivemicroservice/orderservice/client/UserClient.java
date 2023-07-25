package com.reactivemicroservice.orderservice.client;

import com.reactivemicroservice.orderservice.dto.TransactionRequestDto;
import com.reactivemicroservice.orderservice.dto.TransactionResponseDto;
import com.reactivemicroservice.orderservice.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserClient {

    private WebClient webClient;

    public UserClient(@Value("${user.service.url}") String url) {
        this.webClient = WebClient.builder().build();
    }

    public Mono<TransactionResponseDto> authorizeTransaction(TransactionRequestDto requestDto){
        return this.webClient
                .post()
                .uri("/user-transaction")
                .bodyValue(requestDto)
                .retrieve()
                .bodyToMono(TransactionResponseDto.class);
    }


    public Flux<UserDto> getAllUsers(){

        Flux<UserDto> getAllUsers = this.webClient
                .get()
                .uri("/")
                .retrieve()
                .bodyToFlux(UserDto.class);
        return getAllUsers;
    }
}
