package me.daqiang.redis.basic;

import static me.daqiang.redis.RedisUtils.*;

public class basic {

  // string
  public static void setStr(String str) {
    OPS(jedis -> {
      jedis.set("name", str);

      String result = jedis.get("name");
      MYPrint(result);
    });
  }


  public static void main(String[] args) throws Exception {
    setStr(" daqiang zi");

  }

}
