package com.oculus.graphapi;

import com.google.gson.JsonElement;

public final class CodedError {
    public JsonElement errorData;
    public int errorSubcode;
    public String errorUserMessage;
    public String errorUserTitle;
    public String message;
    public String type;
}
