package top.devildyw.consumer.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import top.devildyw.API.DTO.MailDTO;
import top.devildyw.consumer.service.MailSendService;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author Devil
 * @since 2022-08-08-23:41
 */
@Service
@Slf4j
public class MailSendSendServiceImpl implements MailSendService {
    @Resource
    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private JavaMailSenderImpl mailSender;

    @Override
    public void sendMail(MailDTO mailDTO) {
        try {
            String toEmail = mailDTO.getEmail();
            mailSender.setUsername(from);
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setText("这是一条邮件");
            mimeMessageHelper.setSubject("rabbitmq");
            mimeMessageHelper.setFrom(from+"(回声实验室小易)");
            mailSender.send(mimeMessage);
            log.info("邮件发送成功");
        } catch (MessagingException e) {
            log.info("邮件发送失败");
            throw new RuntimeException(e);
        }
    }
}
