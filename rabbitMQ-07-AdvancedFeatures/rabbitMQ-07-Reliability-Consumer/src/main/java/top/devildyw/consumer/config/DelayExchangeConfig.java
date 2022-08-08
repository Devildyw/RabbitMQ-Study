package top.devildyw.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Devil
 * @since 2022-08-08-17:38
 */
@Configuration
public class DelayExchangeConfig {
    @Bean
    public DirectExchange delayedExchange(){
        return ExchangeBuilder.directExchange("delay.direct")
                .delayed() //指定为有延迟功能的交换机
                .durable(true)
                .build();
    }

    @Bean
    public Queue delayedQueue(){
        return new Queue("delay.queue");
    }

    @Bean
    public Binding delayBinding(){
        return BindingBuilder.bind(delayedQueue()).to(delayedExchange()).with("delay");
    }
}
