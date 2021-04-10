package com.facebook.acra.customdata;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Map;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ProxyCustomDataStore implements CustomDataStore {
    private static CustomDataStore dataStore = new ACRACustomDataStore();

    @Override // com.facebook.acra.customdata.CustomDataStore
    public synchronized void setCustomData(String str, @Nullable String str2, Object... objArr) {
        dataStore.setCustomData(str, str2, objArr);
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    @Nullable
    public synchronized String getCustomData(String str) {
        return dataStore.getCustomData(str);
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public synchronized boolean containsKey(String str) {
        return dataStore.containsKey(str);
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public synchronized void removeCustomData(String str) {
        dataStore.removeCustomData(str);
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public synchronized Map<String, String> getSnapshot() {
        return dataStore.getSnapshot();
    }

    public synchronized void setDataStore(CustomDataStore customDataStore) {
        for (Map.Entry<String, String> entry : dataStore.getSnapshot().entrySet()) {
            customDataStore.setCustomData(entry.getKey(), entry.getValue(), new Object[0]);
        }
        dataStore = customDataStore;
    }

    public static ProxyCustomDataStore getInstance() {
        return Holder.CUSTOM_DATA;
    }

    /* access modifiers changed from: private */
    public static class Holder {
        public static final ProxyCustomDataStore CUSTOM_DATA = new ProxyCustomDataStore();

        private Holder() {
        }
    }
}
