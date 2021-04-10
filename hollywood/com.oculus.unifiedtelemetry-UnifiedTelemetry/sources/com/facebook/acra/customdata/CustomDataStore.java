package com.facebook.acra.customdata;

import java.util.Map;
import javax.annotation.Nullable;

public interface CustomDataStore {
    boolean containsKey(String str);

    @Nullable
    String getCustomData(String str);

    Map<String, String> getSnapshot();

    void removeCustomData(String str);

    void setCustomData(String str, @Nullable String str2, Object... objArr);
}
