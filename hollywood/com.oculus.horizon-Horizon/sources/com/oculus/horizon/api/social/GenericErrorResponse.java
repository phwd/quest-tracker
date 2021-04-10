package com.oculus.horizon.api.social;

public class GenericErrorResponse {
    public final Error error;

    public static class Error {
        public final int error_subcode;
        public String message;
        public String type;
    }
}
