package com.facebook.graphservice.interfaces;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Collections;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ConnectionTransientParameters {
    private Map<String, Object> mParameters;

    public ConnectionTransientParameters(Map<String, Object> map) {
        this.mParameters = Collections.unmodifiableMap(map);
    }

    public Map<String, Object> getParameterMap() {
        return this.mParameters;
    }

    public static ConnectionTransientParameters EmptyTransientParameters() {
        return new ConnectionTransientParameters(Collections.emptyMap());
    }
}
