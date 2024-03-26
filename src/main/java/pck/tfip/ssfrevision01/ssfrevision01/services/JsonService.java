package pck.tfip.ssfrevision01.ssfrevision01.services;

import java.io.StringReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonReader;
import pck.tfip.ssfrevision01.ssfrevision01.repos.ProductInfoRepo;

@Service
public class JsonService {
    @Autowired
    ProductInfoRepo productInfoRepo;
    
    //create
    public void saveAllProductsFromArrayToRepo(String jsonArrayString){

        JsonReader jReader = Json.createReader(new StringReader(jsonArrayString));
        JsonArray jArray = jReader.readArray();

        //read all items in array
        for (int x = 0; x < jArray.size(); x++) {
            productInfoRepo.storeToRedis(jArray.getJsonObject(x));
        }
    }

    //read
    
}
