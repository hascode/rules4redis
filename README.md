# Redis JUnit Rules

A simple wrapper for [embedded-redis] to start and stop [Redis] server instances in your integration tests using [JUnit]'s test rules.

# Integration

## Maven

Add the Bintray repository and one dependency ..

```xml
<repository>
    <id>hascode-bintray</id>
    <url>https://dl.bintray.com/hascode/maven</url>
</repository>

<dependency>
  <groupId>com.hascode.r4r</groupId>
  <artifactId>rules4redis</artifactId>
  <version>0.2.0</version>
  <scope>test</scope>
</dependency>
```

## Gradle


```groovy
repositories {
    maven {
        url 'https://dl.bintray.com/hascode/maven'
    }
}
 
dependencies {
  testCompile 'com.hascode.r4r:rules4redis:0.2.0'
}
```

## Example

```java
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
```

## License

Licensed under the [Apache License, Version 2.0]

----

**2017 Micha Kops / hasCode.com**

   [embedded-redis]:https://github.com/kstyrc/embedded-redis
   [Redis]:https://redis.io/
   [JUnit]:http://junit.org/junit4/
   [Apache License, Version 2.0]:https://www.apache.org/licenses/LICENSE-2.0.html

