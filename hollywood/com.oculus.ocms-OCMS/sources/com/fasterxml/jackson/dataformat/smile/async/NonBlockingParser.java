package com.fasterxml.jackson.dataformat.smile.async;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;

public interface NonBlockingParser extends NonBlockingInputFeeder {
    JsonToken peekNextToken() throws IOException, JsonParseException;
}
