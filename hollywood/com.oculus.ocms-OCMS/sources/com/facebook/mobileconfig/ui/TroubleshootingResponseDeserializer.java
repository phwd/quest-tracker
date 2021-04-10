package com.facebook.mobileconfig.ui;

import com.facebook.common.json.FbJsonDeserializer;
import com.facebook.common.json.FbJsonField;
import com.google.common.base.Throwables;
import java.util.HashMap;
import java.util.Map;

public class TroubleshootingResponseDeserializer extends FbJsonDeserializer {
    private static Map<String, FbJsonField> sSchema;

    public TroubleshootingResponseDeserializer() {
        init(TroubleshootingResponse.class);
    }

    @Override // com.facebook.common.json.FbJsonDeserializer
    public FbJsonField getField(String str) {
        FbJsonField fbJsonField;
        synchronized (TroubleshootingResponseDeserializer.class) {
            if (sSchema == null) {
                sSchema = new HashMap();
            } else {
                FbJsonField fbJsonField2 = sSchema.get(str);
                if (fbJsonField2 != null) {
                    return fbJsonField2;
                }
            }
            char c = 65535;
            try {
                int hashCode = str.hashCode();
                if (hashCode != 3556653) {
                    if (hashCode == 96784904) {
                        if (str.equals("error")) {
                            c = 1;
                        }
                    }
                } else if (str.equals("text")) {
                    c = 0;
                }
                if (c == 0) {
                    fbJsonField = FbJsonField.jsonField(TroubleshootingResponse.class.getDeclaredField("text"));
                } else if (c != 1) {
                    return super.getField(str);
                } else {
                    fbJsonField = FbJsonField.jsonField(TroubleshootingResponse.class.getDeclaredField("error"));
                }
                sSchema.put(str, fbJsonField);
                return fbJsonField;
            } catch (Exception e) {
                throw Throwables.propagate(e);
            }
        }
    }
}
