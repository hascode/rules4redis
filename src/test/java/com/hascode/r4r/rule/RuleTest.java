package com.hascode.r4r.rule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import redis.embedded.RedisServer;
import redis.embedded.RedisServerBuilder;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class RuleTest {
    @ClassRule
    public static RedisServerRule rule = new RedisServerRule(new RedisServerBuilder().port(6379));

    static class RedisServerRule extends ExternalResource {
        private RedisServer redisServer;

        public RedisServerRule(RedisServerBuilder builder){
            this.redisServer = builder.build();
        }

        public RedisServerRule(int port) throws IOException {
            this.redisServer = new RedisServer(port);
        }

        @Override
        public Statement apply(Statement base, Description description) {
            System.out.println(description.getDisplayName());
            return super.apply(base, description);
        }

        @Override
        protected void before() throws Throwable {
            redisServer = new RedisServer(6379);
            redisServer.start();
            super.before();
        }

        @Override
        protected void after() {
            redisServer.stop();
            super.after();
        }
    }

    @Test
    public void testSth() {
        Assert.assertEquals("Foo", "Foo");


    }

    @Test
    public void testAnother() {
        Assert.assertEquals(5L, 5L);
    }
}
