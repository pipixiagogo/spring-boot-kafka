package com.zh.springbootkafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

//kafka的配置类
@Configuration
@EnableKafka
@EnableConfigurationProperties(PropertiesConfig.class)
public class KafkaProducerConfig {


    @Autowired
    private PropertiesConfig propertiesConfig;

    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        //配置kafka的基本参数
        //bootstrap.servers   spring.kafka.bootstrap-servers=192.168.1.128:2181
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, propertiesConfig.getServers());
        //retries  spring.kafka.producer.retries=2
        props.put(ProducerConfig.RETRIES_CONFIG, propertiesConfig.getRetries());
        //batch.size  每次批量发送消息的数量
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, propertiesConfig.getBatchSize());
        //buffer.memory 缓存存储
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, propertiesConfig.getBufferMemory());
        //key.serializer  可序列化
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //value.serializer  可序列化
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    public ProducerFactory<String, String> producerFactory() {
        //创建kafka生产者工厂
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {

        return new KafkaTemplate<String, String>(producerFactory());
    }
}
