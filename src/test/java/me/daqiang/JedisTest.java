package me.daqiang;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

/**
 * @ClassName JedisTest
 * @Description classdes
 * @Author daqiang
 * @Date 2020/3/20 7:48 下午
 * @Version 1.0
 **/
public class JedisTest {

    @Test
    public void jedisPoolOps() {
        JedisPoolConfig config = new JedisPoolConfig();
        JedisPool pool = new JedisPool(config, "172.16.10.57", 6379, 10, null, 13);
        try (Jedis jedis = pool.getResource()) {
            jedis.set("MY_AUTH:NAME", "daqiang1");
            String myauth = jedis.get("MY_AUTH:NAME");
            System.out.println(myauth);

            jedis.zadd("sose", 0, "car");
            jedis.zadd("sose", 0, "bike");
            Set<String> sose = jedis.zrange("sose", 0, -1);
            System.out.println(sose);
        }

        pool.close();
    }
}
