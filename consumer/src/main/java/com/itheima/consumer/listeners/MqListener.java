package com.itheima.consumer.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class MqListener {

    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueue(String message) {
        System.out.println("spring 消费者接收到消息：【" + message + "】");
    }

    @RabbitListener(queues = "work.queue")
    public void listenWorkQueue1(String message) throws InterruptedException {
        System.out.println("消费者1接收到消息：【" + message + "】");
        Thread.sleep(20);
    }

    @RabbitListener(queues = "work.queue")
    public void listenWorkQueue2(String message) throws InterruptedException {
        System.err.println("消费者2接收到消息：【" + message + "】");
        Thread.sleep(200);
    }

    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue1(String message) throws InterruptedException {
        System.out.println("消费者1接收到广播消息：【" + message + "】");
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenFanoutQueue2(String message) throws InterruptedException {
        System.out.println("消费者2接收到广播消息：【" + message + "】");
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "direct.queue1", durable = "true"), exchange = @Exchange(name = "hmall.direct", type = ExchangeTypes.DIRECT), key = {"red", "blue"}))
    public void listenDirectQueue1(String message) throws InterruptedException {
        System.out.println("消费者1接收到直连消息：【" + message + "】");
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = "direct.queue2", durable = "true"), exchange = @Exchange(name = "hmall.direct", type = ExchangeTypes.DIRECT), key = {"red", "yellow"}))
    public void listenDirectQueue2(String message) throws InterruptedException {
        System.out.println("消费者2接收到直连消息：【" + message + "】");
    }

    @RabbitListener(queues = "topic.queue1")
    public void listenTopicQueue1(String message) throws InterruptedException {

        System.out.println("消费者1接收到主题消息：【" + message + "】");
    }

    @RabbitListener(queues = "topic.queue2")
    public void listenTopicQueue2(String message) throws InterruptedException {

        System.out.println("消费者2接收到主题消息：【" + message + "】");
    }

    @RabbitListener(queues = "object.queue")
    public void listenObjectQueue(Map<String,Object> message) throws InterruptedException {

        System.out.println("消费者接收到object.queue的消息：【" + message + "】");
    }
}
