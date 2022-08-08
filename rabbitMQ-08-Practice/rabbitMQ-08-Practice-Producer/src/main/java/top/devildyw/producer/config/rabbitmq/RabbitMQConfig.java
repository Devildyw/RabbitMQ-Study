package top.devildyw.producer.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Devil
 * @since 2022-08-08-22:51
 */
@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue mailQueue(){
        return new Queue("mail.queue",true);
    }

    @Bean
    public Exchange mailExchange(){
        return new DirectExchange("mail.exchange"); //默认持久化
    }

    @Bean
    public Binding mailBinding(){
        return BindingBuilder.bind(mailQueue()).to(mailExchange()).with("mail").noargs();
    }
}
