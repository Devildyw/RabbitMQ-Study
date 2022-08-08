package top.devildyw.producer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @author Devil
 * @since 2022-08-07-19:39
 */
@Slf4j
@Configuration
public class CommonConfig implements ApplicationContextAware {
    //实现ApplicationContextAware接口
    //在这里配置RabbitTemplate的全局ReturnCallBack
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //获取RabbitTemplate对象
        RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returned) {
                //记录日志
                log.error("消息发送到队列失败，响应码：{},失败原因：{},交换机：{},路由key：{},消息msg：{}",
                        returned.getReplyCode(),returned.getReplyText(),returned.getExchange(),returned.getRoutingKey(),returned.getMessage());
                //更具需求可以配置消息重发
            }
        });
    }
}
