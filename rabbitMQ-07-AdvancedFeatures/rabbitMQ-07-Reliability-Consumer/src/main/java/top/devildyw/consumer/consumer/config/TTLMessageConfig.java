package top.devildyw.consumer.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Devil
 * @since 2022-08-08-13:16
 */
@Configuration
public class TTLMessageConfig {
    @Bean
    public DirectExchange ttlDirectExchange(){
        return new DirectExchange("ttl.direct");
    }

    @Bean
    public Queue ttlQueue(){
        return QueueBuilder.durable("ttl.queue")
                .ttl(10000) //指定超时存活时间
                .deadLetterExchange("dl.direct") //指定死信交换机 这里超过存活时间队列就会将消息投递到死信交换机中
                .deadLetterRoutingKey("dl") //指定死信交换机与死信队列之间的routingkey 到时投递的消息都会发送到死信交换机绑定的routingkey对应的队列中
                .build();
    }

    @Bean
    public Binding ttlBinding(){
        return BindingBuilder.bind(ttlQueue()).to(ttlDirectExchange()).with("ttl");
    }
}
