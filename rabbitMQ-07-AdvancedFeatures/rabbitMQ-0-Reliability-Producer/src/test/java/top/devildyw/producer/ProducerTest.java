package top.devildyw.producer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.SuccessCallback;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Devil
 * @since 2022-08-04-22:23
 */
@SpringBootTest
@Slf4j
public class ProducerTest {
    //注入Rabbit 模板类
    @Resource
    RabbitTemplate rabbitTemplate;

    /**
     * 确认模式
     * 步骤:
     *  1. 确认模式开启: connectionFactory中开启publisher-confirms = "true" (配置文件中)
     *  2. 在rabbitTemplate中定义ConfirmCallBack回调函数
     */
    @Test
    public void TestConfirm(){
        //1.准备消息
        String message = "hello, spring amqp!";
        //2. 准备CorrelationData
        //2.1 准备ConfirmCallback
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        ReturnedMessage returned = correlationData.getReturned();
        correlationData.getFuture().addCallback(result -> {
            //判断结果
            if (result.isAck()){
                //ACK
                System.out.println("消息投递到交换机成功！消息ID:"+correlationData.getId());
                log.info("消息投递到交换机成功！消息ID：{}",correlationData.getId());
            }else{
                //NACK
                System.out.println("消息投递到交换机失败！消息ID:"+correlationData.getId());
                log.error("消息投递到交换机失败！消息ID：{}",correlationData.getId());
            }
        },ex -> { //出现异常导致消息发送失败
            //发送消息失败
            //记录日志
            log.error("消息发送失败！",ex);
            //根据需求可以重发消息
        });
        //发送消息
        rabbitTemplate.convertAndSend("amp.topic","simple.test",message,correlationData);
    }

    @Test
    public void testTTLMessage(){
        //1. 消息准备
        Message message = MessageBuilder.withBody("ttl message".getBytes(StandardCharsets.UTF_8))
                .setExpiration("5000") //设置5秒超时时间
                .build();
        //2. 发送消息
        rabbitTemplate.convertAndSend("ttl.direct","ttl",message);
        //记录日志
        log.info("消息已经成功发送！");
    }

    @Test
    public void testDelayedMsg() {
        //创建消息
        Message message = MessageBuilder.withBody("hello,delayed message".getBytes(StandardCharsets.UTF_8))
                .setHeader("x-delay", 10000) //设置head 延迟属性 延迟10秒
                .build();
        //消息ID,需要封装到CorrelationData中
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        //发送消息
        rabbitTemplate.convertAndSend("delay.direct","delay",message,correlationData);
        log.debug("发送消息成功");
        while (true){

        }
    }

    @Test
    public void testLazyQueueMsg() {
        for (int i = 0; i < 1000000; i++) {
            Message message = MessageBuilder.withBody("hello,Spring".getBytes(StandardCharsets.UTF_8))
                    .setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT) //设置消息为非持久化消息
                    .build();
            //发送消息
            rabbitTemplate.convertAndSend("lazy.queue",message);
        }
    }
}
