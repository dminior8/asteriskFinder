package pl.dminior8.asteriskfinder;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
    private JsonService jsonServiceMock;
    @Mock
    private JsonParser jsonParserMock;

    private JsonService jsonService = new JsonService();
    private JsonParser jsonParser = new JsonParser();

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void shouldGetFilePath() {
        String expectedFilePath = "src/main/resources/pl/dminior8/asteriskfinder/example.json";
        when(jsonServiceMock.getFilePath("example")).thenReturn(expectedFilePath);
        when(jsonServiceMock.getFilePath("example.jso")).thenReturn("example.jso");
        when(jsonServiceMock.getFilePath("example.json")).thenReturn(expectedFilePath);
        when(jsonServiceMock.getFilePath("")).thenReturn(".json");

        assertAll(
                ()->assertEquals(expectedFilePath, jsonServiceMock.getFilePath("example")),
                ()->assertEquals("example.jso", jsonServiceMock.getFilePath("example.jso")),
                ()->assertEquals(expectedFilePath, jsonServiceMock.getFilePath("example.json")),
                ()->assertEquals(".json", jsonServiceMock.getFilePath(""))
        );
    }

    @Test
    public void shouldCheckIsValidJsonFile() throws IOException {
        String jsonString = "{\"key\": \"value\"}";

        when(bufferedReader.readLine()).thenReturn(jsonString).thenReturn(null);
        when(jsonParserMock.parse(any(Reader.class))).thenReturn(new JsonObject());

        JsonElement actualElement = jsonParserMock.parse(bufferedReader);

        assertTrue(actualElement.isJsonObject());
    }

    @Test
    public void shouldCheckForValue() {
        String[] jsonStrings = {
                "{\"key\": \"expectedValue\"}",
                "{\"nested\": {\"key\": \"expectedValue\"}}",
                "{\"array\": [{\"key\": \"expectedValue\"}]}",
                "{\"array\": [{\"key1\": \"value1\"}, {\"key\": \"expectedValue\"}]}",
                "{\"otherKey\": \"otherValue\"}"
        };
        String key = "key";
        String value = "expectedValue";

        int it=0;
        for (String jsonString : jsonStrings) {
            String actualValue = jsonService.checkForValue(new JsonParser().parse(jsonString), key, value);
            if(it == 4){
                assertEquals("", actualValue);
            }else{
                assertEquals(value, actualValue);
            }
            it++;
        }
    }
}

