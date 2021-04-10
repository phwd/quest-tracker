package com.oculus.horizon.api.media;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.cast.CastHTTPServerForMobileDevice;
import com.oculus.http.core.base.ApiRequest;
import java.util.UUID;

public class CastNotifRequest extends ApiRequest<CastNotifResponse> {
    public final String mCastServerUrl;
    public final String mSessionId;

    public CastNotifRequest(String str, String str2) {
        if (str != null) {
            this.mCastServerUrl = str;
            if (str2 != null) {
                this.mSessionId = str2;
                return;
            }
        }
        throw null;
    }

    public ImmutableMap<String, String> getParams() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("client_mutation_id", UUID.randomUUID().toString());
        A01.put(CastHTTPServerForMobileDevice.INTENT_KEY_CAST_SERVER_URL, this.mCastServerUrl);
        A01.put("session_id", this.mSessionId);
        return A01.build();
    }
}
