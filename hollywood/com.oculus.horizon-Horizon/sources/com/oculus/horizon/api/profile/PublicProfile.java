package com.oculus.horizon.api.profile;

import X.AnonymousClass0NO;
import X.C03080c5;
import X.C08780ya;
import java.util.Map;
import javax.annotation.Nullable;

public class PublicProfile {
    public static final String TAG = "PublicProfile";
    @Nullable
    public Map<String, Map<String, String>> value;

    public PublicProfile fromDataBlob(DataBlobResponse dataBlobResponse) {
        try {
            this.value = (Map) new C08780ya().A05(dataBlobResponse.data_blob, Map.class);
            return this;
        } catch (C03080c5 e) {
            AnonymousClass0NO.A0B(TAG, "Can't parse the data blob string as JSON, invalid syntax.", e);
            return this;
        }
    }
}
