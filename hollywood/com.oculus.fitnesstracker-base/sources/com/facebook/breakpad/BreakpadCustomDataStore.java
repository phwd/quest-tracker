package com.facebook.breakpad;

import com.facebook.acra.customdata.CustomDataStore;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class BreakpadCustomDataStore implements CustomDataStore {
    @Override // com.facebook.acra.customdata.CustomDataStore
    public String getCustomData(String str) {
        return BreakpadManager.getCustomData(str);
    }

    public void removeCustomData(String str) {
        BreakpadManager.removeCustomData(str);
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public void setCustomData(String str, String str2, Object... objArr) {
        BreakpadManager.setCustomData(str, str2, objArr);
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public Map<String, String> getSnapshot() {
        return BreakpadManager.getCustomDataSnapshot();
    }

    public boolean containsKey(String str) {
        return BreakpadManager.containsKey(str);
    }
}
