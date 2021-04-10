package com.facebook.common.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.base.Throwables;
import java.io.IOException;
import java.lang.reflect.Constructor;
import javax.annotation.Nullable;

public class FbJsonDeserializer extends JsonDeserializer<Object> {
    protected Class<?> mClass;

    @Nullable
    public FbJsonField getField(String str) {
        return null;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public boolean isCachable() {
        return true;
    }

    /* access modifiers changed from: protected */
    public final void init(Class<?> cls) {
        this.mClass = cls;
    }

    /* access modifiers changed from: protected */
    public Object callDefaultConstructor() throws Exception {
        try {
            Constructor<?> declaredConstructor = this.mClass.getDeclaredConstructor(new Class[0]);
            declaredConstructor.setAccessible(true);
            return declaredConstructor.newInstance(new Object[0]);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(this.mClass.getName() + " missing default constructor", e);
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        try {
            Object callDefaultConstructor = callDefaultConstructor();
            while (FbJsonChecker.nextTokenOrThrow(jsonParser) != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    jsonParser.nextToken();
                    FbJsonField field = getField(currentName);
                    if (field != null) {
                        field.deserialize(callDefaultConstructor, jsonParser, deserializationContext);
                    } else {
                        jsonParser.skipChildren();
                    }
                }
            }
            if (callDefaultConstructor instanceof Postprocessable) {
                ((Postprocessable) callDefaultConstructor).postprocess();
            }
            return callDefaultConstructor;
        } catch (Exception e) {
            Throwables.propagateIfPossible(e, IOException.class);
            FbJsonUtil.throwDeserializationFailure(this.mClass, jsonParser, e);
            throw new RuntimeException("not reached");
        }
    }
}
