package com.facebook.acra.customdata;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Formatter;
import java.util.Map;
import java.util.TreeMap;

@Nullsafe(Nullsafe.Mode.LOCAL)
final class ACRACustomDataStore implements CustomDataStore {
    private final Map<String, String> mInstanceCustomParameters = new TreeMap();

    ACRACustomDataStore() {
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public final String getCustomData(String str) {
        return this.mInstanceCustomParameters.get(str);
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public final void setCustomData(String str, String str2, Object... objArr) {
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
            this.mInstanceCustomParameters.remove(str);
        }
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public final Map<String, String> getSnapshot() {
        return new TreeMap(this.mInstanceCustomParameters);
    }
}
