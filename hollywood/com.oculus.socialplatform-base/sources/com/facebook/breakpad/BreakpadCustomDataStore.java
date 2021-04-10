package com.facebook.breakpad;

import com.facebook.acra.customdata.CustomDataStore;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Map;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class BreakpadCustomDataStore implements CustomDataStore {
    @Override // com.facebook.acra.customdata.CustomDataStore
    public boolean containsKey(String str) {
        return BreakpadManager.containsKey(str);
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    @Nullable
    public String getCustomData(String str) {
        return BreakpadManager.getCustomData(str);
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public Map<String, String> getSnapshot() {
        return BreakpadManager.getCustomDataSnapshot();
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public void removeCustomData(String str) {
        BreakpadManager.removeCustomData(str);
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public void setCustomData(String str, @Nullable String str2, Object... objArr) {
        BreakpadManager.setCustomData(str, str2, objArr);
    }
}
