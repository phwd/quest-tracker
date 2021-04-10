package com.facebook.acra.customdata;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class ProxyCustomDataStore implements CustomDataStore {
    private static CustomDataStore dataStore = new ACRACustomDataStore();

    /* access modifiers changed from: package-private */
    public static class Holder {
        public static final ProxyCustomDataStore CUSTOM_DATA = new ProxyCustomDataStore();
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public final synchronized void setCustomData(String str, String str2, Object... objArr) {
        dataStore.setCustomData(str, str2, objArr);
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public final synchronized String getCustomData(String str) {
        return dataStore.getCustomData(str);
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public final synchronized Map<String, String> getSnapshot() {
        return dataStore.getSnapshot();
    }

    public final synchronized void setDataStore(CustomDataStore customDataStore) {
        for (Map.Entry<String, String> entry : dataStore.getSnapshot().entrySet()) {
            customDataStore.setCustomData(entry.getKey(), entry.getValue(), new Object[0]);
        }
        dataStore = customDataStore;
    }

    public static ProxyCustomDataStore getInstance() {
        return Holder.CUSTOM_DATA;
    }
}
