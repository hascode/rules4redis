package com.hascode.r4r.rule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.embedded.RedisServerBuilder;

public class RuleTest {
    @ClassRule
    public static RedisServerRule rule = new RedisServerRule(new RedisServerBuilder().port(6379));

    @Test
    public void shouldWriteAndReadFromRedisInstance() {
        Jedis jedis = new Jedis("localhost", 6379);
        Assert.assertEquals(jedis.get("somekey"), null);
        jedis.set("somekey", "1234");
        Assert.assertEquals(jedis.get("somekey"), "1234");
    }
}
