package pck.tfip.ssfrevision01.ssfrevision01.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import pck.tfip.ssfrevision01.Utils.Util;
import pck.tfip.ssfrevision01.ssfrevision01.services.JsonService;

@Repository
public class ProductInfoRepo {

    @Autowired
    JsonService reader;
    
    @Autowired
    @Qualifier(Util.REDIS_ONE)
    RedisTemplate<String, String> template;

    HashOperations<String, String, Json> hashOps;

    //initialize redis
    public void startRedisWithProducts(){
        hashOps = template.opsForHash();
        hashOps.putIfAbsent(Util.KEY_PRODUCTS, null, null)
    }
}
