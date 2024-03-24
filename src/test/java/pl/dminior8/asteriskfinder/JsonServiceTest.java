package pl.dminior8.asteriskfinder;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.dminior8.asteriskfinder.service.JsonService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class JsonServiceTest {
    @Mock
    private BufferedReader bufferedReader;
    @Mock
    private JsonService jsonService;
    @Mock
    private JsonParser jsonParser;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void shouldGetFilePath() {
        String expectedFilePath = "src/main/resources/pl/dminior8/asteriskfinder/example.json";
        when(jsonService.getFilePath("example")).thenReturn(expectedFilePath);
        when(jsonService.getFilePath("example.jso")).thenReturn("example.jso");
        when(jsonService.getFilePath("example.json")).thenReturn(expectedFilePath);
        when(jsonService.getFilePath("")).thenReturn(".json");

        assertAll(
                ()->assertEquals(expectedFilePath, jsonService.getFilePath("example")),
                ()->assertEquals("example.jso", jsonService.getFilePath("example.jso")),
                ()->assertEquals(expectedFilePath, jsonService.getFilePath("example.json")),
                ()->assertEquals(".json", jsonService.getFilePath(""))
        );
    }

    @Test
    public void shouldCheckIsValidJsonFile() throws IOException {
        String jsonString = "{\"key\": \"value\"}";

        when(bufferedReader.readLine()).thenReturn(jsonString).thenReturn(null);
        when(jsonParser.parse(any(Reader.class))).thenReturn(new JsonObject());

        JsonElement actualElement = jsonParser.parse(bufferedReader);

        assertTrue(actualElement.isJsonObject());
    }


    @Test
    public void shouldCheckForValue() {
        String jsonString = "{\"key\": \"expectedValue\"}";
        JsonElement jsonElement = new JsonParser().parse(jsonString).getAsJsonObject();
        String expectedValue = "value";
        when(jsonService.checkForValue(jsonElement, "key", "value")).thenReturn(expectedValue);

        when(jsonService.checkForValue(jsonElement,"wrongKey","value")).thenReturn("");

        String correctActualValue = jsonService.checkForValue(jsonElement, "key", "value");
        String wrongActualValue = jsonService.checkForValue(jsonElement, "wrongKey", "value");

        assertAll(
                ()->assertEquals(expectedValue, correctActualValue),
                ()->assertEquals("", wrongActualValue)
        );
    }

    @Test
    public void shouldCheckForValueRecursively() {
        String jsonString = "{\"outerKey\": {\"innerKey\": \"expectedValue\"}}";
        JsonObject jsonObject = jsonParser.parse(jsonString).getAsJsonObject();

        String key = "innerKey";
        String value = "expectedValue";

        String actualValue = jsonService.checkForValue(jsonObject, key, value);

        assertEquals(value, actualValue);
    }
}

