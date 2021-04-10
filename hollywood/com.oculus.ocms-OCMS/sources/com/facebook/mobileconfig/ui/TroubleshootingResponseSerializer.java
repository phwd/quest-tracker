package com.facebook.mobileconfig.ui;

import com.facebook.common.json.AutoGenJsonHelper;
import com.facebook.common.json.FbSerializerProvider;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class TroubleshootingResponseSerializer extends JsonSerializer<TroubleshootingResponse> {
    static {
        FbSerializerProvider.addSerializerToCache(TroubleshootingResponse.class, new TroubleshootingResponseSerializer());
    }

    public void serialize(TroubleshootingResponse troubleshootingResponse, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (troubleshootingResponse == null) {
            jsonGenerator.writeNull();
        }
        jsonGenerator.writeStartObject();
        serializeFields(troubleshootingResponse, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndObject();
    }

    public static void serializeFields(TroubleshootingResponse troubleshootingResponse, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        AutoGenJsonHelper.write(jsonGenerator, serializerProvider, "text", troubleshootingResponse.text);
        AutoGenJsonHelper.write(jsonGenerator, serializerProvider, "error", troubleshootingResponse.error);
    }
}
