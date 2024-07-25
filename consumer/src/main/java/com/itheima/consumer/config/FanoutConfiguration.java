package com.itheima.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfiguration {

    // 创建一个 fanout 类型的交换机
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("hmall.fanout2");
    }

    // 创建一个队列
    @Bean
    public Queue fanoutQueue3() {
        return new Queue("fanout.queue3");
    }

    // 将队列绑定到交换机上
    @Bean
    public Binding fanoutBinding3(FanoutExchange fanoutExchange, Queue fanoutQueue3) {
        return BindingBuilder.bind(fanoutQueue3).to(fanoutExchange);
    }

    // 创建第二个队列
    @Bean
    public Queue fanoutQueue4() {
        return new Queue("fanout.queue4");
    }


    // 将第二个队列绑定到交换机上
    @Bean
    public Binding fanoutBinding4(FanoutExchange fanoutExchange, Queue fanoutQueue4) {
        return BindingBuilder.bind(fanoutQueue4).to(fanoutExchange);
    }
}
