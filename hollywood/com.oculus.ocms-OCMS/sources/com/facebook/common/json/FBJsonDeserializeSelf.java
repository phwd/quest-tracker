package com.facebook.common.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;

public interface FBJsonDeserializeSelf {
    Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException;
}
