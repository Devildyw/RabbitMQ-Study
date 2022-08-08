package top.devildyw.consumer.service;

import top.devildyw.API.DTO.MailDTO;

/**
 * @author Devil
 * @since 2022-08-08-23:40
 */
public interface MailSendService {

    void sendMail(MailDTO mailDTO);
}
