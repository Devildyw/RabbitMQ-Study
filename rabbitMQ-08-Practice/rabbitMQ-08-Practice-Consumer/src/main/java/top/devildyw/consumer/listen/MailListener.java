package top.devildyw.consumer.listen;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import top.devildyw.API.DTO.MailDTO;
import top.devildyw.consumer.service.MailSendService;

import javax.annotation.Resource;

/**
 * @author Devil
 * @since 2022-08-08-23:38
 */
@Component
public class MailListener {
    @Resource
    MailSendService mailSendService;

    @RabbitListener(queues = "mail.queue")
    public void mailMessageListen(MailDTO mailDTO){
        mailSendService.sendMail(mailDTO);
    }
}
