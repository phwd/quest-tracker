package com.facebook.common.json;

import com.facebook.infer.annotation.Nullsafe;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.google.common.base.Throwables;
import java.io.IOException;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class FBJsonSelfDeserializer extends FbJsonDeserializer {
    @Override // com.facebook.common.json.FbJsonDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        try {
            return ((FBJsonDeserializeSelf) callDefaultConstructor()).deserialize(jsonParser, deserializationContext);
        } catch (Exception e) {
            Throwables.propagateIfPossible(e, IOException.class);
            FbJsonUtil.throwDeserializationFailure(this.mClass, jsonParser, e);
            throw new RuntimeException("not reached");
        }
    }
}
