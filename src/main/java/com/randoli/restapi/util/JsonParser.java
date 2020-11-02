package com.randoli.restapi.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.randoli.restapi.model.Batch;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class JsonParser {
    final static String urlJsonFile = "https://raw.githubusercontent.com/" +
            "mateusmascarenhas/rest-api/main/src/main/resources/json/payload.json";

    public static Batch ConvertJsonToPojo() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        var batch = mapper.readValue(new URL(urlJsonFile), Batch.class);
        return batch;
    }
}
