package ru.ssau.esa.response;

public class GoodResponse extends ServerResponse {

    public GoodResponse(String message) {
        super(true, message);
    }

    public GoodResponse() {
        super(true, "");
    }
}
