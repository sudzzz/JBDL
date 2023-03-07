package com.example.demoredis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class PersonConfig {

    @Bean
    public LettuceConnectionFactory getConnectionFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration =
                new RedisStandaloneConfiguration("redis-17776.c282.east-us-mz.azure.cloud.redislabs.com", 17776);

        redisStandaloneConfiguration.setPassword("Jba8LgSyLj2xCuiKw41PtEI3ISWyjvTr");

        LettuceConnectionFactory lettuceConnectionFactory
                = new LettuceConnectionFactory(redisStandaloneConfiguration);

        return lettuceConnectionFactory;

    }

    @Bean
    public RedisTemplate<String, Object> getTemplate(){

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        redisTemplate.setConnectionFactory(getConnectionFactory());

        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public ObjectMapper getMapper(){
        return new ObjectMapper();
    }
}
