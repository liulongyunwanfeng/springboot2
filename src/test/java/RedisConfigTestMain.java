import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

/**
 * @author liulongyun
 * @create 2019/6/1 9:34
 **/
public class RedisConfigTestMain {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisTemplate redisTemplate = context.getBean(RedisTemplate.class);

        // 这杨直接用 redisTemplate来执行，执行一个命令独立用到一个redis连接，执行完了就关闭连接，下一个执行开启连接，执行完关闭
        // 很多场景中，我们一个业务需要执行多个redis操作，当然不希望在开启多个连接来执行，这样浪费资源
        // 可以用户SessionCallBack和RedisCallback (推荐使用SessionCallback)
        redisTemplate.opsForValue().set("jediskey1","jedisValue1");
        redisTemplate.opsForHash().put("jedisHashKey1","email","llydjango123@aliyun.com");


        // 可以用户SessionCallBack和RedisCallback (推荐使用SessionCallback),在一次连接中执行多个redis操作
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {

                redisTemplate.opsForValue().set("jediskey2","jedisValue2");
                redisTemplate.opsForHash().put("jedisHashKey2","email2","llydjango123@aliyun.com");
                return null;
            }
        });

        ((AnnotationConfigApplicationContext) context).close();
    }
}
