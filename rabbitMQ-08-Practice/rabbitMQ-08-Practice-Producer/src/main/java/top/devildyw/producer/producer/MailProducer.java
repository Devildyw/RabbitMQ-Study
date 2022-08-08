package top.devildyw.producer.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.SuccessCallback;
import top.devildyw.API.DTO.MailDTO;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author Devil
 * @since 2022-08-08-23:31
 */
@Component
@Slf4j
public class MailProducer {
    @Resource
    RabbitTemplate rabbitTemplate;
    public void sendMailByMQ(MailDTO mailDTO) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        correlationData.getFuture().addCallback(new SuccessCallback<CorrelationData.Confirm>() {
            @Override
            public void onSuccess(CorrelationData.Confirm result) {
                if (result.isAck()){
                    log.info("消息投递到交换机成功！");
                }else{
                    log.error("消息投递到交换机失败！");
                }
            }
        }, new FailureCallback() {
            @Override
            public void onFailure(Throwable ex) { //出现异常导致消息发送失败
                log.error("消息发送失败");
            }
        });


        //发送消息
        rabbitTemplate.convertAndSend("mail.queue",mailDTO);
    }
}
