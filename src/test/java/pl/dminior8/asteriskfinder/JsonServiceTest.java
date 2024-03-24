package pl.dminior8.asteriskfinder;

import com.google.gson.JsonElement;
import org.junit.jupiter.api.Test;
import pl.dminior8.asteriskfinder.service.JsonService;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class JsonServiceTest {


    @Test
    public void shouldGetFilePathWithJsonExtension() {
        String fileName = "example.json";
        String expectedFilePath = "src/main/resources/pl/dminior8/asteriskfinder/example.json";
        assertEquals(expectedFilePath, JsonService.getFilePath(fileName));
    }

    @Test
    public void shouldGetFilePathWithoutJsonExtension() {
        String fileName = "example";
        String expectedFilePath = "src/main/resources/pl/dminior8/asteriskfinder/example.json";
        assertEquals(expectedFilePath, JsonService.getFilePath(fileName));
    }
    @Test
    public void shouldGetFilePathWithWrongExtension() {
        String fileName = "example";
        String expectedFilePath = "src/main/resources/pl/dminior8/asteriskfinder/example.json";
        assertEquals(expectedFilePath, JsonService.getFilePath(fileName));
    }

}

