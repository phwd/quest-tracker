package com.oculus.horizon.api.profile;

import com.facebook.debug.log.BLog;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.Map;
import javax.annotation.Nullable;

public class PublicProfile {
    private static final String TAG = "PublicProfile";
    @Nullable
    public Map<String, Map<String, String>> value;

    public PublicProfile fromDataBlob(DataBlobResponse dataBlobResponse) {
        try {
            this.value = (Map) new Gson().fromJson(dataBlobResponse.data_blob, Map.class);
            return this;
        } catch (JsonSyntaxException e) {
            BLog.e(TAG, "Can't parse the data blob string as JSON, invalid syntax.", e);
            return this;
        }
    }
}
