package top.devildyw.consumer.consumer.producer.producer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Devil
 * @since 2022-08-01-12:47
 */
public class Consumer_HelloWorld {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1. 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //2. 设置参数
        connectionFactory.setHost("36.137.128.27"); //端口默认值 5672
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

        //接收消息
        /*
        String basicConsume(String queue, boolean autoAck, Consumer callback) throws IOException;
        参数:
        1. queue: 队列名称
        2. autoAck: 是否自动确认
        3. callback: 回调对象
         */
        DefaultConsumer consumer = new DefaultConsumer(channel){
            /*
            回调方法,当收到消息后,会自动执行该方法
            1. consumerTag: 标识
            2. envelope: 获取一些信息,交换机,路由key
            3. properties: 配置信息
            4. body: 数据
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumerTag: "+consumerTag);
                System.out.println("Exchange: "+envelope.getExchange());
                System.out.println("RoutingKey: "+envelope.getRoutingKey());
                System.out.println("Properties: "+properties);
                System.out.println("body: "+new String(body));
            }
        };
        channel.basicConsume("hello_world",true,consumer);
    }

    //消费者会去一直监听队列中的信息,不能够关闭资源
}
