package top.devildyw.consumer.producer;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import top.devildyw.consumer.producer.config.RabbitMQConfig;

import javax.annotation.Resource;

/**
 * @author Devil
 * @since 2022-08-01-19:43
 */
@SpringBootTest
public class ProducerTest {
    //注入RabbitTemplate
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Test
    public void testSend(){
        rabbitTemplate.setExchange(RabbitMQConfig.EXCHANGE_NAME);
        rabbitTemplate.convertAndSend("boot.haha","boot mq hello~~");
    }
}
