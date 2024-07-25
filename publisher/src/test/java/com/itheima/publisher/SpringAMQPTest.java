package com.itheima.publisher;


import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringAMQPTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMessage2Queue() {

        String queueName = "simple.queue";
        String message = "Hello, RabbitMQ!";
        rabbitTemplate.convertAndSend(queueName, message);
    }

    @Test
    public void testWorkQueue() throws InterruptedException {
        String queueName = "work.queue";
        for (int i = 0; i < 50; i++) {
            // 模拟一秒内产生50个消息
            String message = "Hello, worker,message_" + i;
            Thread.sleep(20);
            rabbitTemplate.convertAndSend(queueName, message);
        }
    }

    @Test
    public void testFanout() {
        String exchangeName = "hmall.fanout";
        String message = "Hello, Fanout!";
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }

    @Test
    public void testSendDirect() {
        String exchangeName = "hmall.direct";
        String message = "Hello, direct!";
        rabbitTemplate.convertAndSend(exchangeName, "blue", message);
    }

    @Test
    public void testSendTopic() {
        String exchangeName = "hmall.topic";
        String message = "中国新闻！！天气不错";
        rabbitTemplate.convertAndSend(exchangeName, "china.weather", message);
    }
}
