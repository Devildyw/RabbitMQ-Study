package top.devildyw.consumer.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author Devil
 * @since 2022-08-07-19:11
 */
@Component
@Slf4j
public class SpringRabbitListener {
//    @RabbitListener(queues = "test_queue_confirm")
//    public void listenSimpleQueue(String msg){
//        System.out.println("消费者接收到simple.queue的消息:"+msg);
//        System.out.println(1/0); //手动使其报错   测试消费者ack模式
//    }


//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(name = "dl.queue",durable = "true"),
//            exchange = @Exchange(name = "dl.direct"),
//            key = "dl"
//    ))
//    public void listenDlQueue(String msg){
//        log.info("消费者接收到了dl.queue的延迟消息");
//    }

//    @RabbitListener(queues = "delay.queue")
//    public void listenDelayedQueue(String msg){
//        log.info("接收到 delay.queue的延迟消息：{}",msg);
//    }
//
//    @RabbitListener(queuesToDeclare = @Queue(
//            name = "lazy.queue",
//            durable = "true",
//            arguments = @Argument(name = "x-queue-mode",value = "lazy")
//    ))
//    public void listenLazyQueue(String msg){
//        log.info("接收到 lazy,queue的消息：{}",msg);
//    }
}
