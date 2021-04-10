package com.facebook.acra.customdata;

import java.util.Map;

public interface CustomDataStore {
    String getCustomData(String str);

    Map<String, String> getSnapshot();

    void setCustomData(String str, String str2, Object... objArr);
}
