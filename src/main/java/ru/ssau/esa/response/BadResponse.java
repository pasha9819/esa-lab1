package ru.ssau.esa.response;

public class BadResponse extends ServerResponse {

    private static final String BAD_PARAM_PATTERN = "Conversion of '%s=%s' failed!";

    public BadResponse(String message) {
        super(false, message);
    }

    public static BadResponse badParameterConversion(String name, String value){
        return new BadResponse(String.format(BAD_PARAM_PATTERN, name, value));
    }
}
