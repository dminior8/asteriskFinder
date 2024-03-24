package pl.dminior8.asteriskfinder.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonService {

    public String getFilePath(String fileName) {
        StringBuilder extension = new StringBuilder("");
        StringBuilder newFileName = new StringBuilder(fileName);

        if(!fileName.toLowerCase().endsWith(".json")){
            int dotIndex = fileName.indexOf('.');
            if(dotIndex == -1){
                extension = new StringBuilder(".json");
            }
        }
        System.out.println("Nazwa pliku to: " + newFileName + extension);
        return "src/main/resources/pl/dminior8/asteriskfinder/" + newFileName + extension;
    }

    public JsonElement isValidJsonFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath(fileName)))) {
            StringBuilder subString = new StringBuilder();

            while (reader.ready()) {
                subString.append(reader.readLine());
            }
            String content = subString.toString();
            JsonParser parser = new JsonParser();

            return parser.parse(content);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (com.google.gson.JsonSyntaxException e) { //Jeśli zła składnia
            e.printStackTrace();
        }
        return null;
    }

    public String checkForValue(JsonElement jsonElement, String key, String value){
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        if (jsonObject.has(key)) {
            String result = new String(jsonObject.get(key).getAsString());
            if(result.isEmpty()){
                return "";
            }else{
                return result;
            }
        }else{
            StringBuilder nestedValue = new StringBuilder("");
            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) { //Sprawdzenie po mapie klucz-wartość (JsonElement)
                JsonElement jsonElementNew = entry.getValue();

                if(jsonElementNew.isJsonObject()){ //przypadek gdy obiektem: {}
                    nestedValue = new StringBuilder(checkForValue(jsonElementNew, key, value));
                }else if(jsonElementNew.isJsonArray()){ //przypadek gdy tablicą: []
                    for (JsonElement element : jsonElementNew.getAsJsonArray()) {
                        jsonObject = element.getAsJsonObject();
                        nestedValue = new StringBuilder(checkForValue(jsonObject, key, value));
                    }
                }
                if(!nestedValue.toString().equals("")){
                    return nestedValue.toString();
                }
            }
        }
        return "";
    }
}
//if(arrayJsonElement.isJsonObject() ){
//nestedValue = new StringBuilder(
//        checkForValue(arrayJsonElement.getAsJsonObject(), key, value));
//        }else if(arrayJsonElement.isJsonArray()){
//nestedValue = new StringBuilder(
//        checkForValue(arrayJsonElement.getAsJsonArray(), key, value));
//        }
//        if(nestedValue.toString().equals("null")){
//        return nestedValue.toString();
//                }