package com.hascode.r4r.rule;

import org.junit.rules.ExternalResource;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import redis.embedded.RedisServer;
import redis.embedded.RedisServerBuilder;

import java.io.IOException;

/**
 * Allows to start an embedded Redis database or connect to an existing one using JUnit rules.
 *
 * @since 1.0.0
 */
public class RedisServerRule extends ExternalResource {
    private final RedisServer redisServer;

    /**
     * Creates a new redis server rule by given RedisServerBuilder instance.
     * @param builder the server builder.
     */
    public RedisServerRule(RedisServerBuilder builder){
        this.redisServer = builder.build();
    }

    /**
     * Creates a new redis server rule running on localhost with the specified port.
     * @param port the port.
     * @throws IOException if initialization fails
     */
    public RedisServerRule(int port) throws IOException {
        this.redisServer = new RedisServer(port);
    }

    @Override
    protected void before() throws Throwable {
        super.before();
        redisServer.start();
    }

    @Override
    protected void after() {
        super.after();
        redisServer.stop();
    }
}