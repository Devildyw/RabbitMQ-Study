package top.devildyw.consumer.consumer.consumer.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

/**
 * @author Devil
 * @since 2022-08-01-19:58
 */
@Component
public class RabbitMQListener {

    //指定queue的名称
    @RabbitListener(queues = "boot_topic_queue")
    public void listenerQueue(Message message){
        System.out.println(message);
    }
}
