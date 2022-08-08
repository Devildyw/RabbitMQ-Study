package top.devildyw.producer.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Devil
 * @since 2022-08-01-19:33
 */
@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "boot_topic_exchange";
    public static final String QUEUE_NAME = "boot_topic_queue";

    //配置交换机
    @Bean("bootExchange")
    public Exchange bootExchange(){
        //声明一个topic类型的交换机
        Exchange exchange = ExchangeBuilder.topicExchange(EXCHANGE_NAME)
                .durable(true) //是否持久化
                .build();
        return exchange;
    }

    //配置队列
    @Bean("bootQueue")
    public Queue bootQueue(){
        Queue queue = QueueBuilder.durable(QUEUE_NAME).build();
        return queue;
    }


    //队列和交换机绑定关系 Binding

    /**
     * 1. 队列
     * 2. 交换机
     * 3. routing key
     * @param queue 队列
     * @param exchange 交换机
     * @return binding
     */
    @Bean("bootQueueExchange")
    public Binding bootQueueExchange(@Qualifier("bootQueue") Queue queue, @Qualifier("bootExchange") Exchange exchange){
        Binding binding = BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
        return binding;
    }
}
