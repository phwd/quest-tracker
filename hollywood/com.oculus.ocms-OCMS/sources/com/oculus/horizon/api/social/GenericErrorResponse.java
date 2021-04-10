package com.oculus.horizon.api.social;

public class GenericErrorResponse {
    public Error error;

    public static class Error {
        public int error_subcode;
        public String message;
        public String type;
    }
}
