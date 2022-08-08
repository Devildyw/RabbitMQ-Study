package top.devildyw.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Devil
 * @since 2022-08-01-12:18
 */
public class Producer_HelloWord {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1. 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //2. 设置参数
        connectionFactory.setHost("localhost"); //端口默认值 5672
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/"); //虚拟机 默认值/
        connectionFactory.setUsername("admin"); //用户名 默认guest
        connectionFactory.setPassword("admin"); //用户名 默认guest
        //3. 获取对应连接 Connection
        Connection connection = connectionFactory.newConnection();
        //4. 创建Channel
        Channel channel = connection.createChannel();
        //5. 创建队列Queue

        /*
        Queue.DeclareOk queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete,
                                 Map<String, Object> arguments) throws IOException;
         参数：
         1. queue: 队列名称
         2. durable: 是否持久化，当mq重启之后,还在
         3. exclusive:
            * 是否独占。只能能有一个消费者监听这个队列(仅限于此连接 如果该链接关闭队列也会删除)
         4. autoDelete: 是否自动删除。当没有Consumer时，自动删除掉
         5. argument: 参数
         */
        //如果没有一个名字叫作“hello_world”的队列,则会创建该队列,如果有则不会创建
        channel.queueDeclare("hello_world",true,false,false,null);
        //6.发送消息
        /*
        void basicPublish(String exchange, String routingKey, boolean mandatory, BasicProperties props, byte[] body)
            throws IOException;

        参数:
        1. exchange: 交换机名称。简单模式下交换机会使用默认的 “”
        2. routingKey: 路由配置
        3. props: 配置信息
        4. body: 发送消息数据
         */
        String body = "hello rabbitmq~~~";

        channel.basicPublish("","hello_word",null,body.getBytes());


        //7. 释放资源
        channel.close();
        connection.close();
    }
}