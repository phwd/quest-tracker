package com.facebook.common.json;

import com.facebook.common.json.JsonLogger;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class JsonLoggerStub implements JsonLogger {
    public static final String JACKSON_FALLBACK_TAG = "JACKSON_FALLBACK";

    @Override // com.facebook.common.json.JsonLogger
    public void log(JsonLogger.Event event, String str, Object obj) {
        if (obj == null) {
            obj = "<unknown>";
        }
        BLog.i(JACKSON_FALLBACK_TAG, "Using %s to %s %s", obj, event == JsonLogger.Event.SERIALIZATION ? "serialize" : "deserialize", str);
    }
}
