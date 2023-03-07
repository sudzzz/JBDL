package com.example.L2324MajorProjectEWallet.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.Properties;

@Configuration
public class WalletConfig {

    public Properties getProperties(){
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        return properties;
    }

    public ConsumerFactory getConsumerFactory(){
        return new DefaultKafkaConsumerFactory(getProperties());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory getListener(){
        ConcurrentKafkaListenerContainerFactory listener = new ConcurrentKafkaListenerContainerFactory<>();
        listener.setConsumerFactory(getConsumerFactory());
        return listener;
    }

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }
}
