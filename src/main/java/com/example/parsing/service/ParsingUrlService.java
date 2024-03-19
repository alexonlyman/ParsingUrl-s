package com.example.parsing.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/*
URL processing service
 */
@Service
@RequiredArgsConstructor
public class ParsingUrlService {
    private WritingToDb writeToDb;
    public void parsingUrl(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int response = connection.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder resp = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        resp.append(line);
                    }
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonNode = objectMapper.readTree(resp.toString());
                    cuttingJson(jsonNode);
                }
            }
            connection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    private void cuttingJson(JsonNode jsonNode) {
        String title;
        for (JsonNode node : jsonNode) {
            if (node.has("title") && node.get("title").isTextual()) {
                title = node.get("title").asText();
                writeToDb.writeToDatabase(title);
            } else {
                System.out.println("поле не найдено");
            };
        }
    }

}
