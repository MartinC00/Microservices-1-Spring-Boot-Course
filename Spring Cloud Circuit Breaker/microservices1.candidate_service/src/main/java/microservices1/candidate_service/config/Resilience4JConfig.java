package microservices1.candidate_service.config;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;

import java.time.Duration;

@Configuration
public class Resilience4JConfig {
    private CircuitBreakerConfig config = CircuitBreakerConfig.custom()
            .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
            .slidingWindowSize(6)
            .failureRateThreshold(50)
            .waitDurationInOpenState(Duration.ofMillis(40000))
            .build();
    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> globalCustomConfiguration(){
        // global config
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(config)
                .build());
    }
//        For specific circuit breaker configuration
//        return factory -> factory.configure(builder -> builder
//        		  .circuitBreakerConfig(config)
//        	      .build(), "circuit1");
}
