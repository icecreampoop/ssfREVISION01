package pck.tfip.ssfrevision01.ssfrevision01.services;

import java.io.StringReader;

import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class JsonService {
    
    public static String productJsonToString(Json jsonString){
        jso
        
        return "";
    }

    public static JsonArray initializeProductStringToJsonObject(String initialProductsJsonString){
        JsonReader jReader = Json.createReader(new StringReader(initialProductsJsonString));
        return jReader.readObject();
    }
}
