package com.oculus.horizon.api.media;

import com.facebook.acra.ACRA;
import com.google.common.collect.ImmutableMap;
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
            throw null;
        }
        throw null;
    }

    public ImmutableMap<String, String> getParams() {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("client_mutation_id", UUID.randomUUID().toString());
        A04.put("cast_server_url", this.mCastServerUrl);
        A04.put(ACRA.SESSION_ID_KEY, this.mSessionId);
        return A04.build();
    }
}
