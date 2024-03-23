package pl.dminior8.asteriskfinder.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonService {

    public static boolean isFileExists() {
        return false;
    }
    public static String getFilePath(String fileName) {
        StringBuilder extension = null;
        StringBuilder newFileName = new StringBuilder(fileName);
        if(!fileName.toLowerCase().endsWith(".json")){
            int dotIndex = fileName.indexOf('.');
            if(dotIndex != -1){
                newFileName = new StringBuilder(fileName.substring(0, dotIndex));
            }
            extension = new StringBuilder(".json");
            System.out.println("Wprowadzona nazwa pliku to: " + newFileName);
        }
        return "src/main/resources/pl/dminior8/asteriskfinder/" + newFileName + extension;
    }

    public static JsonElement isValidJsonFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath(fileName)))) {
            StringBuilder subString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                subString.append(line);
            }
            String content = subString.toString();
            JsonParser parser = new JsonParser();

            return parser.parse(content);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (com.google.gson.JsonSyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
