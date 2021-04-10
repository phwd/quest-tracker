package com.facebook.common.json;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class JsonParseRuntimeException extends RuntimeException {
    public JsonParseRuntimeException() {
    }

    public JsonParseRuntimeException(String str) {
        super(str);
    }

    public JsonParseRuntimeException(String str, Throwable th) {
        super(str, th);
    }

    public JsonParseRuntimeException(Throwable th) {
        super(th);
    }
}
