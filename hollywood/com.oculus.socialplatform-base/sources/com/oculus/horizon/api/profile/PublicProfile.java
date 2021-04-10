package com.oculus.horizon.api.profile;

import X.AnonymousClass0MD;
import X.AnonymousClass0eR;
import X.AnonymousClass13N;
import java.util.Map;
import javax.annotation.Nullable;

public class PublicProfile {
    public static final String TAG = "PublicProfile";
    @Nullable
    public Map<String, Map<String, String>> value;

    public PublicProfile fromDataBlob(DataBlobResponse dataBlobResponse) {
        try {
            this.value = (Map) new AnonymousClass13N().A05(dataBlobResponse.data_blob, Map.class);
            return this;
        } catch (AnonymousClass0eR e) {
            AnonymousClass0MD.A07(TAG, "Can't parse the data blob string as JSON, invalid syntax.", e);
            return this;
        }
    }
}
