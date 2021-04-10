package com.facebook.acra.customdata;

import java.util.Formatter;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.Nullable;

class ACRACustomDataStore implements CustomDataStore {
    private final Map<String, String> mInstanceCustomParameters = new TreeMap();

    ACRACustomDataStore() {
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    @Nullable
    public String getCustomData(String key) {
        return this.mInstanceCustomParameters.get(key);
    }

    public void removeCustomData(String key) {
        if (key != null) {
            this.mInstanceCustomParameters.remove(key);
        }
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public void setCustomData(String key, @Nullable String fmt, Object... args) {
        if (key != null) {
            if (fmt != null) {
                String value = fmt;
                if (args.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    Formatter formatter = new Formatter(sb);
                    formatter.format(fmt, args);
                    formatter.close();
                    value = sb.toString();
                }
                this.mInstanceCustomParameters.put(key, value);
                return;
            }
            removeCustomData(key);
        }
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public Map<String, String> getSnapshot() {
        return new TreeMap(this.mInstanceCustomParameters);
    }
}
