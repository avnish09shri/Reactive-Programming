package com.reactivemicroservice.userservice.controller;

import com.reactivemicroservice.userservice.dto.TransactionRequestDto;
import com.reactivemicroservice.userservice.dto.TransactionResponseDto;
import com.reactivemicroservice.userservice.entity.UserTransaction;
import com.reactivemicroservice.userservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user")
public class UserTransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("user-transaction")
    public Mono<TransactionResponseDto> createTransaction(@RequestBody Mono<TransactionRequestDto> requestDtoMono){
        return requestDtoMono.flatMap(this.transactionService::createTransaction);
    }

    @GetMapping("user-transaction")
    public Flux<UserTransaction> getByUserId(@RequestParam("userId") int userId){
        return this.transactionService.getByUserId(userId);
    }
}
