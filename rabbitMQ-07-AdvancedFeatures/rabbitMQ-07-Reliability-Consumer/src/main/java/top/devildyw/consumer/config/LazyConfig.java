package top.devildyw.consumer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Devil
 * @since 2022-08-08-18:15
 */
@Configuration
public class LazyConfig {
    @Bean
    public Queue lazyQueue(){
        return QueueBuilder.durable("lazy.queue")
                .lazy() //开启x-queue-mode为lazy
                .build();
    }
}
