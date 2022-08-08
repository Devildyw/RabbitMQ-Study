package top.devildyw.producer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Devil
 * @since 2022-08-08-22:21
 */
@Configuration
public class QuorumQueueConfig {
    @Bean
    public Queue quorumQueue(){
        return QueueBuilder.durable("quorum.queue2")
                .quorum() //设置为仲裁队列
                .build();
    }
}
