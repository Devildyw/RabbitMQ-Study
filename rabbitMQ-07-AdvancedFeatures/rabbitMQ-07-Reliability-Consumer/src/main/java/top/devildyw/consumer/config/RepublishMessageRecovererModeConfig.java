package top.devildyw.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class RepublishMessageRecovererModeConfig {
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("error.direct"); //创建一个交换机 用于专门处理(重发)消费失败的消息
    }

    @Bean
    public Queue errorQueue(){
        return new Queue("error.queue",true); //与上面专门处理消费失败的交换机相绑定的缓存消息的队列
    }

    /**
     * 定义队列与交换机绑定关系
     * @return
     */
    @Bean
    public Binding errorBinding(){
        return BindingBuilder.bind(errorQueue()).to(directExchange()).with("error");
    }

    @Resource
    RabbitTemplate rabbitTemplate;

    /**
     * 配置消息重发模式
     * @return
     */
    @Bean
    public MessageRecoverer republishMessageRecoverer(){
        return new RepublishMessageRecoverer(rabbitTemplate,"error.direct","error");
    }
}
