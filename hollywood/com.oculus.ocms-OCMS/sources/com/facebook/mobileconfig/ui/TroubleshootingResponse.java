package com.facebook.mobileconfig.ui;

import com.facebook.common.json.AutoGenJsonDeserializer;
import com.facebook.common.json.AutoGenJsonSerializer;
import com.facebook.common.json.FbObjectMapper;
import com.fasterxml.jackson.annotation.JsonProperty;

@AutoGenJsonDeserializer
@AutoGenJsonSerializer
public class TroubleshootingResponse {
    @JsonProperty("error")
    public String error = null;
    @JsonProperty("text")
    public String text = null;

    public static TroubleshootingResponse deserialize(String str) {
        if (str.isEmpty() || str.codePointAt(0) == 123) {
            try {
                return (TroubleshootingResponse) FbObjectMapper.getInstance().readValue(str, TroubleshootingResponse.class);
            } catch (Exception e) {
                TroubleshootingResponse troubleshootingResponse = new TroubleshootingResponse();
                troubleshootingResponse.error = e.getMessage();
                return troubleshootingResponse;
            }
        } else {
            TroubleshootingResponse troubleshootingResponse2 = new TroubleshootingResponse();
            troubleshootingResponse2.error = str;
            return troubleshootingResponse2;
        }
    }
}
