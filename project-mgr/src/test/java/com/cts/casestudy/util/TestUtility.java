package com.cts.casestudy.util;

import static java.nio.charset.Charset.forName;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.io.IOException;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
 
public class TestUtility {
 
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(APPLICATION_JSON.getType(), 
    														  APPLICATION_JSON.getSubtype(), forName("utf8"));
    private static final ObjectMapper mapper = new ObjectMapper();

 
    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
 
    public static String createStringWithLength(int length) {
        StringBuilder builder = new StringBuilder();
 
        for (int index = 0; index < length; index++) {
            builder.append("a");
        }
 
        return builder.toString();
    }
}