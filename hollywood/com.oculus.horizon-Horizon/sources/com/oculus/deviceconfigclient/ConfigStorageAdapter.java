package com.oculus.deviceconfigclient;

import X.AnonymousClass0NO;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.deviceconfigclient.shared.StorageInternalRepresentation;
import com.oculus.deviceconfigclient.shared.StorageParam;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ConfigStorageAdapter {
    public static final String TAG = "ConfigStorageAdapter";

    public static <Type> void A00(StorageInternalRepresentation storageInternalRepresentation, String str, Map<String, ValueInfo<Type>> map) {
        for (Map.Entry<String, ValueInfo<Type>> entry : map.entrySet()) {
            String key = entry.getKey();
            ValueInfo<Type> value = entry.getValue();
            Type type = value.mValueForSerialization;
            if (type == null) {
                AnonymousClass0NO.A0E(TAG, "No value set for param name %s", key);
            } else {
                storageInternalRepresentation.Params.add(new StorageParam(key, str, type.toString(), value.mLoggingId, value.mSessionless));
            }
        }
    }
}
