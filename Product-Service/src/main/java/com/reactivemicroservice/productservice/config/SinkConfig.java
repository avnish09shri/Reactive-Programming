package com.reactivemicroservice.productservice.config;

import com.reactivemicroservice.productservice.dto.ProductDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Configuration
public class SinkConfig {

    @Bean
    public Sinks.Many<ProductDTO> sink(){
        return Sinks.many().replay().limit(1);
    }

    @Bean
    public Flux<ProductDTO> productBroadCast(Sinks.Many<ProductDTO> sink){
        return sink.asFlux();
    }
}
