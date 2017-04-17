package com.hascode.r4r.rule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import redis.clients.jedis.Jedis;
import redis.embedded.RedisServer;
import redis.embedded.RedisServerBuilder;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

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
