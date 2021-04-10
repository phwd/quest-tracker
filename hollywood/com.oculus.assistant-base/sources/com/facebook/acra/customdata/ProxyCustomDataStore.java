package com.facebook.acra.customdata;

import java.util.Map;

public class ProxyCustomDataStore implements CustomDataStore {
    public static CustomDataStore dataStore = new ACRACustomDataStore();

    public class Holder {
        public static final ProxyCustomDataStore CUSTOM_DATA = new ProxyCustomDataStore();
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public synchronized boolean containsKey(String str) {
        return dataStore.containsKey(str);
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public synchronized String getCustomData(String str) {
        return dataStore.getCustomData(str);
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public synchronized Map getSnapshot() {
        return dataStore.getSnapshot();
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public synchronized void removeCustomData(String str) {
        dataStore.removeCustomData(str);
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public synchronized void setCustomData(String str, String str2, Object... objArr) {
        dataStore.setCustomData(str, str2, objArr);
    }

    public synchronized void setDataStore(CustomDataStore customDataStore) {
        for (Map.Entry entry : dataStore.getSnapshot().entrySet()) {
            customDataStore.setCustomData((String) entry.getKey(), (String) entry.getValue(), new Object[0]);
        }
        dataStore = customDataStore;
    }

    public static ProxyCustomDataStore getInstance() {
        return Holder.CUSTOM_DATA;
    }
}
