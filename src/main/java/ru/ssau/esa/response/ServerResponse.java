package ru.ssau.esa.response;

import com.google.gson.Gson;

public abstract class ServerResponse {

    private static final Gson converter = new Gson();

    private final boolean success;
    private final String message;

    protected ServerResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    @Override
    public String toString() {
        return converter.toJson(this);
    }
}
