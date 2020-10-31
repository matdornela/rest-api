package com.randoli.restapi.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.randoli.restapi.model.Batch;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class JsonParser {
    final static String pathJsonFile = "src/main/resources/json/payload.json";

    public static Batch ConvertJsonToPojo() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        var json = new File(pathJsonFile).getAbsolutePath();
        var batch = mapper.readValue(Paths.get(json).toFile(), Batch.class);
        return batch;
    }
}
