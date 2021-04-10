package com.facebook.common.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;

public class FbJsonChecker {
    public static final JsonToken nextTokenOrThrow(JsonParser jsonParser) throws IOException {
        JsonToken nextToken = jsonParser.nextToken();
        throwIfEndOfInput(nextToken);
        return nextToken;
    }

    public static final void throwIfEndOfInput(JsonToken jsonToken) throws IOException {
        if (jsonToken == null) {
            throw new IOException("Unexpected end of json input");
        }
    }
}
