package com.lot.controller;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "fanout.B")//监听的队列名称 TestDirectQueue
public class TopicManReceiver {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("B消费者收到消息  : " + testMessage.toString());
    }
}
