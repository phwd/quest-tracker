package com.facebook.acra.customdata;

import java.util.Map;

public class ProxyCustomDataStore implements CustomDataStore {
    private static CustomDataStore dataStore = new ACRACustomDataStore();

    /* access modifiers changed from: private */
    public static class Holder {
        public static final ProxyCustomDataStore CUSTOM_DATA = new ProxyCustomDataStore();
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public synchronized void setCustomData(String key, String format, Object... args) {
        dataStore.setCustomData(key, format, args);
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public synchronized String getCustomData(String key) {
        return dataStore.getCustomData(key);
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public synchronized Map<String, String> getSnapshot() {
        return dataStore.getSnapshot();
    }

    public synchronized void setDataStore(CustomDataStore newStore) {
        for (Map.Entry<String, String> entry : dataStore.getSnapshot().entrySet()) {
            newStore.setCustomData(entry.getKey(), entry.getValue(), new Object[0]);
        }
        dataStore = newStore;
    }

    public static ProxyCustomDataStore getInstance() {
        return Holder.CUSTOM_DATA;
    }
}
