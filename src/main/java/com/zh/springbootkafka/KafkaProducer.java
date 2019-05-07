package com.zh.springbootkafka;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;

@Component
@EnableScheduling
public class KafkaProducer {

    @Autowired
    KafkaTemplate kafkaTemplate;
    @Scheduled(cron = "00/1 * * * * ?")
    public void send(){
        String message = UUID.randomUUID().toString();
        byte[] bytes={(byte)0xEF,(byte)0xEE};
        JSONObject json = new JSONObject();
        json.put("RDBSN", "123456");
//          json.put("TIME",device.getBmuUptTime())
        json.put("DATA", ByteUtil.toHexString( bytes ));
        json.put("NUM",123);
        json.put("TIME",System.currentTimeMillis()/1000);
        ListenableFuture future = kafkaTemplate.send("test", json.toJSONString());

        future.addCallback(o -> System.out.println("send-消息发送成功：" + json), throwable -> System.out.println("消息发送失败：" + json));
    }

    @KafkaListener(topics = {"test"})
    public void receive(String content){
        System.err.println("Receive:" + content);
    }
}
