package com.facebook.acra.customdata;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Formatter;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
class ACRACustomDataStore implements CustomDataStore {
    private final Map<String, String> mInstanceCustomParameters = new TreeMap();

    ACRACustomDataStore() {
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    @Nullable
    public String getCustomData(String str) {
        return this.mInstanceCustomParameters.get(str);
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public boolean containsKey(String str) {
        return this.mInstanceCustomParameters.containsKey(str);
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public void removeCustomData(String str) {
        if (str != null) {
            this.mInstanceCustomParameters.remove(str);
        }
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public void setCustomData(String str, @Nullable String str2, Object... objArr) {
        if (str != null) {
            if (str2 != null) {
                if (objArr.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    Formatter formatter = new Formatter(sb);
                    formatter.format(str2, objArr);
                    formatter.close();
                    str2 = sb.toString();
                }
                this.mInstanceCustomParameters.put(str, str2);
                return;
            }
            removeCustomData(str);
        }
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public Map<String, String> getSnapshot() {
        return new TreeMap(this.mInstanceCustomParameters);
    }
}
