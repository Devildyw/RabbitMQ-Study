package top.devildyw.producer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.devildyw.producer.service.MailService;

import javax.annotation.Resource;

/**
 * @author Devil
 * @since 2022-08-08-23:27
 */
@RestController
@RequestMapping("mail")
public class MailController {
    @Resource
    private MailService mailService;

    @GetMapping
    public String SendMail(String email){
        boolean b = mailService.sendMail(email);
        return b?"成功":"失败";
    }
}
