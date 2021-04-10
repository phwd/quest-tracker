package com.oculus.horizon.cloudstorage2.model;

import java.util.HashMap;

public class CloudStorage2ActionMap extends HashMap<CloudStorage2FileAction, String[]> {
    public final int A00() {
        int i;
        String[] strArr = (String[]) get(CloudStorage2FileAction.ADD);
        String[] strArr2 = (String[]) get(CloudStorage2FileAction.UPDATE);
        int i2 = 0;
        if (strArr != null) {
            i = strArr.length;
        } else {
            i = 0;
        }
        int i3 = i + 0;
        if (strArr2 != null) {
            i2 = strArr2.length;
        }
        return i3 + i2;
    }
}
