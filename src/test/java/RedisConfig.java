import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author liulongyun
 * @create 2019/6/1 9:23
 * Spring 中redis的使用
 *
 **/
@Configuration
public class RedisConfig {

    private RedisConnectionFactory connectionFactory = null;

    @Bean(name = "RedisConnectionFactory")
    public RedisConnectionFactory initRedisConnectionFactory(){

        if(connectionFactory!=null){
            return  this.connectionFactory;
        }

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最多连接数
        poolConfig.setMaxTotal(50);
        // 最大空闲连接数
        poolConfig.setMaxIdle(30);
        // 最大等待毫秒数
        poolConfig.setMaxWaitMillis(2000l);

        JedisConnectionFactory connectionFactory  = new JedisConnectionFactory(poolConfig);

        RedisStandaloneConfiguration resCfg = connectionFactory.getStandaloneConfiguration();
        resCfg.setHostName("127.0.0.1");
        resCfg.setPort(6379);
        // 没有密码就不用设置
//        resCfg.setPassword("123456");

        this.connectionFactory = connectionFactory;

        return connectionFactory;


    }


    @Bean(name = "redisTemplate")
    public RedisTemplate<Object,Object> initRedisTemplate(){

        RedisTemplate<Object,Object> redisTemplate = new RedisTemplate<>();

        redisTemplate.setConnectionFactory(initRedisConnectionFactory());

        // 设置序列化方式 将key 和value都用RedisSerializer 进行序列化
        RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();

        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);


        return  redisTemplate;


    }



}
