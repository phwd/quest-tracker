package com.facebook.acra.info;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExternalProcessInfo {
    protected final LinkedHashMap<String, String> acraFields;

    public Map<String, String> getAcraFields() {
        return Collections.unmodifiableMap(this.acraFields);
    }
}
