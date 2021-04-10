package com.facebook.breakpad;

import com.facebook.acra.customdata.CustomDataStore;
import java.util.Map;
import javax.annotation.Nullable;

public class BreakpadCustomDataStore implements CustomDataStore {
    @Override // com.facebook.acra.customdata.CustomDataStore
    @Nullable
    public String getCustomData(String key) {
        return BreakpadManager.getCustomData(key);
    }

    public void removeCustomData(String key) {
        BreakpadManager.removeCustomData(key);
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public void setCustomData(String key, @Nullable String fmt, Object... args) {
        BreakpadManager.setCustomData(key, fmt, args);
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public Map<String, String> getSnapshot() {
        return BreakpadManager.getCustomDataSnapshot();
    }

    public boolean containsKey(String key) {
        return BreakpadManager.containsKey(key);
    }
}
