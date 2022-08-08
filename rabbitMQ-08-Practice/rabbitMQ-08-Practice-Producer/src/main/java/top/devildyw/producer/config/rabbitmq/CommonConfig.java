package top.devildyw.producer.config.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class CommonConfig implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RabbitTemplate template = applicationContext.getBean(RabbitTemplate.class);
        template.setReturnsCallback((returned)->{
            //记录日志
            log.error("消息发送到队列失败，响应码：{},失败原因：{},交换机：{},路由key：{},消息msg：{}",
                    returned.getReplyCode(),returned.getReplyText(),returned.getExchange(),returned.getRoutingKey(),returned.getMessage());
            //更具需求可以配置消息重发
        });
    }
}