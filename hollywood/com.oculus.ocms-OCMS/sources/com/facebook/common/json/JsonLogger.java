package com.facebook.common.json;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public interface JsonLogger {

    public enum Event {
        SERIALIZATION,
        DESERIALIZATION
    }

    void log(Event event, String str, Object obj);
}
