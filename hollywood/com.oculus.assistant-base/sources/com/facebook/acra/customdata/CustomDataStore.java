package com.facebook.acra.customdata;

import java.util.Map;

public interface CustomDataStore {
    boolean containsKey(String str);

    String getCustomData(String str);

    Map getSnapshot();

    void removeCustomData(String str);

    void setCustomData(String str, String str2, Object... objArr);
}
