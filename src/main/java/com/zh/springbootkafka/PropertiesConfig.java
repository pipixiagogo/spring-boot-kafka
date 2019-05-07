package com.zh.springbootkafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class PropertiesConfig {
    private static final Logger log = LoggerFactory.getLogger( PropertiesConfig.class );
    public static final String MONGO_HOST = "mongo.host";
    public static final String MONGO_PORT = "mongo.port";
    public static final String MONGO_DB = "mongo.database";
    public static final String MONGO_USERNAME = "mongo.username";
    public static final String MONGO_PASSWORD = "mongo.password";
    public static final String EXTENDS_CONFIG_FILE = "extends.config.file";


    @Value("${spring.kafka.bootstrap-servers}")
    private String servers;

    @Value("${spring.kafka.producer.retries}")
    private int retries;

    @Value("${spring.kafka.producer.batch-size}")
    private int batchSize;

    @Value("${spring.kafka.producer.buffer-memory}")
    private int bufferMemory;
    @Value("${kafka.send.topic}")
    private String kafkaTopic;






    public String getServers() {
        return servers;
    }


    public int getRetries() {
        return retries;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public int getBufferMemory() {
        return bufferMemory;
    }



}
