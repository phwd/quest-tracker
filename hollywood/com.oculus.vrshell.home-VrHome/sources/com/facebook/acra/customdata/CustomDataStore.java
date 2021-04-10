package com.facebook.acra.customdata;

import java.util.Map;
import javax.annotation.Nullable;

public interface CustomDataStore {
    @Nullable
    String getCustomData(String str);

    Map<String, String> getSnapshot();

    void setCustomData(String str, @Nullable String str2, Object... objArr);
}
