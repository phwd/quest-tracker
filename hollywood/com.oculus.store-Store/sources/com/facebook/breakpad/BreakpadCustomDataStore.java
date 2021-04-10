package com.facebook.breakpad;

import com.facebook.acra.customdata.CustomDataStore;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Map;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class BreakpadCustomDataStore implements CustomDataStore {
    @Override // com.facebook.acra.customdata.CustomDataStore
    @Nullable
    public String getCustomData(String key) {
        return BreakpadManager.getCustomData(key);
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
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

    @Override // com.facebook.acra.customdata.CustomDataStore
    public boolean containsKey(String key) {
        return BreakpadManager.containsKey(key);
    }
}
