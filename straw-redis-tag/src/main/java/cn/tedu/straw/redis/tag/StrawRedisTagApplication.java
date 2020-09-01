package cn.tedu.straw.redis.tag;

import io.netty.handler.ssl.util.SimpleTrustManagerFactory;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("cn.tedu.straw.redis.tag.mapper")
public class StrawRedisTagApplication {

    public static void main(String[] args) {
        SpringApplication.run(StrawRedisTagApplication.class, args);
    }
        @Bean
        public RedisTemplate<String,Object> redisTemplate(LettuceConnectionFactory connectionFactory){
            RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
            //设置key的序列化工具
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            //设置Value的序列化工具  使用的是Jcakson框架中的序列化工具
            redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
            //再设置连接Reedis服务器的连接工厂
            redisTemplate.setConnectionFactory(connectionFactory);
            return redisTemplate;
        }
}
