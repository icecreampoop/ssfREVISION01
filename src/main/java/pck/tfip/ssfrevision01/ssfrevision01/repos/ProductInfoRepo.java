package pck.tfip.ssfrevision01.ssfrevision01.repos;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.json.JsonObject;
import pck.tfip.ssfrevision01.Utils.Util;
import pck.tfip.ssfrevision01.ssfrevision01.models.ProductModel;

@Repository
public class ProductInfoRepo {

    @Autowired
    @Qualifier(Util.REDIS_ONE)
    RedisTemplate<String, String> template;

    HashOperations<String, String, String> hashOps;

    public void storeToRedis(JsonObject product) {
        hashOps = template.opsForHash();
        hashOps.putIfAbsent(Util.KEY_PRODUCTS, product.get("id").toString(), product.toString());
    }

    public String sanitizeJsonString(String jsonStringFromRedis) {
        return jsonStringFromRedis.replace("\\", "")
                            .replace("\"", "")
                            .replace("{", "")
                            .replace("}", "");
    }

    public ArrayList<ProductModel> readProductListFromRedis() {
        ArrayList<ProductModel> productList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        hashOps = template.opsForHash();

        // this for each loop uses the hashkey to return all keys
        for (String x : hashOps.keys(Util.KEY_PRODUCTS)) {

            //object mapper to auto map json string to model
            try {
                productList.add(objectMapper.readValue(hashOps.get(Util.KEY_PRODUCTS, x), ProductModel.class));
            } catch (IOException io) {
                io.printStackTrace();
            }
        }

        return productList;
    }
}
