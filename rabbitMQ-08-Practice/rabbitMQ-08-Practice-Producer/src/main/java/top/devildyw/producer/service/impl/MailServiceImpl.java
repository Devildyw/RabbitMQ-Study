package top.devildyw.producer.service.impl;

import org.junit.platform.commons.util.StringUtils;
import org.springframework.stereotype.Service;
import top.devildyw.API.DTO.MailDTO;
import top.devildyw.producer.producer.MailProducer;
import top.devildyw.producer.service.MailService;

import javax.annotation.Resource;

/**
 * @author Devil
 * @since 2022-08-08-23:30
 */
@Service
public class MailServiceImpl implements MailService {
    @Resource
    MailProducer mailProducer;
    @Override
    public boolean sendMail(String email) {
        if (StringUtils.isBlank(email)){
            return false;
        }
        //封装信息
        MailDTO mailDTO = new MailDTO();
        mailDTO.setEmail(email);

        mailProducer.sendMailByMQ(mailDTO);
        return true;
    }
}
