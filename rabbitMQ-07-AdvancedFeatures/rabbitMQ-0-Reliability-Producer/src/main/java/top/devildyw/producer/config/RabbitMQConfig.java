package top.devildyw.producer.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Devil
 * @since 2022-08-04-19:52
 *
 * 配置类
 */
@Configuration
public class RabbitMQConfig {
    @Bean("getExchange")
    public Exchange exchange(){
        Exchange exchange = ExchangeBuilder.topicExchange("amp.topic").durable(true).build();
        return exchange;
    }

    @Bean("getQueue")
    public Queue queue(){
        Queue queue = QueueBuilder.durable("test_queue_confirm").build();
        return queue;
    }

    @Bean
    public Binding binding(@Qualifier("getExchange") Exchange exchange, @Qualifier("getQueue") Queue queue){
        Binding binding = BindingBuilder.bind(queue).to(exchange).with("simple.test").noargs();
        return binding;
    }

}
