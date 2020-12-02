package ru.ssau.esa.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class ServerResponse {

    private static final ObjectMapper converter = new ObjectMapper();

    private final boolean success;
    private final String message;

    protected ServerResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        try {
            return converter.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{\"success\": false, \"message\": \"Server Error\"}";
        }
    }
}
