package com.facebook.acra.customdata;

import java.util.Formatter;
import java.util.Map;
import java.util.TreeMap;

public class ACRACustomDataStore implements CustomDataStore {
    public final Map mInstanceCustomParameters = new TreeMap();

    @Override // com.facebook.acra.customdata.CustomDataStore
    public boolean containsKey(String str) {
        return this.mInstanceCustomParameters.containsKey(str);
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public String getCustomData(String str) {
        return (String) this.mInstanceCustomParameters.get(str);
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public Map getSnapshot() {
        return new TreeMap(this.mInstanceCustomParameters);
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public void removeCustomData(String str) {
        if (str != null) {
            this.mInstanceCustomParameters.remove(str);
        }
    }

    @Override // com.facebook.acra.customdata.CustomDataStore
    public void setCustomData(String str, String str2, Object... objArr) {
        if (str == null) {
            return;
        }
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
