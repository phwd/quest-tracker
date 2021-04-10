package com.facebook.graphservice.interfaces;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Map;
import java.util.TreeMap;

@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class ConnectionTransientParameterBuilder {
    protected Map<String, Object> mParameters = new TreeMap();

    public ConnectionTransientParameters build() {
        return new ConnectionTransientParameters(this.mParameters);
    }
}
