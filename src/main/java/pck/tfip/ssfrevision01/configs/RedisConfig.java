package pck.tfip.ssfrevision01.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import pck.tfip.ssfrevision01.Utils.Util;

@Configuration
public class RedisConfig {
    
    //copied from notes
    @Value("${spring.data.redis.host}")
    private String redisHost;
    @Value("${spring.data.redis.port}")
    private Integer redisPort;
    @Value("${spring.data.redis.username}")
    private String redisUsername;
    @Value("${spring.data.redis.password}")
    private String redisPassword;
    @Value("${spring.data.redis.database}")
    private Integer redisDatabase;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        redisConfig.setHostName(redisHost);
        redisConfig.setPort(redisPort);
        redisConfig.setDatabase(redisDatabase);

        //if there is a username set the username/password
        if (!"NOT_SET".equals(redisUsername.trim())) {
            redisConfig.setUsername(redisUsername);
            redisConfig.setPassword(redisPassword);
        }

        //IRL, this part might change as it could be a different driver
        JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();
        JedisConnectionFactory jedisConnFac = new JedisConnectionFactory(redisConfig,jedisClient);

        return jedisConnFac;
    }

    @Bean(Util.REDIS_ONE)
    public RedisTemplate<String, String> redisTemplate() {

        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());

        return template;
    }
}
