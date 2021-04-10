package com.oculus.restwrapper;

import com.google.gson.JsonElement;

public class ErrorResponse {
    public Error error;

    public class Error {
        public JsonElement error_data;
        public int error_subcode;
        public String error_user_message;
        public String error_user_title;
        public String message;
        public String type;

        public Error() {
        }
    }
}
