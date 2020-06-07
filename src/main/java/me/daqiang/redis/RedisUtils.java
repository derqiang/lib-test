package me.daqiang.redis;

import java.util.function.Consumer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtils {

  public static final JedisPool pool;
  static {
    JedisPoolConfig config = new JedisPoolConfig();
    config.setMaxIdle(8);
    config.setMaxTotal(8);
    pool = new JedisPool(config, "192.168.0.100", 6379, 2000);
  }

  public static void OPS(Consumer<Jedis> doit) {
    if (doit != null) {
      Jedis jedis = pool.getResource();
      jedis.select(5);
      doit.accept(jedis);
      jedis.close();
      pool.close();
    }
  }

  public static void MYPrint(String input) {
    System.out.println("redis testing ... " + input);
  }
}
