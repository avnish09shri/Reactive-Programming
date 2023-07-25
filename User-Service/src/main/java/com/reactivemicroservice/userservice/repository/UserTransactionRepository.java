package com.reactivemicroservice.userservice.repository;

import com.reactivemicroservice.userservice.entity.UserTransaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserTransactionRepository extends ReactiveCrudRepository<UserTransaction, Integer> {

    Flux<UserTransaction> findByUserId(int userId);
}
