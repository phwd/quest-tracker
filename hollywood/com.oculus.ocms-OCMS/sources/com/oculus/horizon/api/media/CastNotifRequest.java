package com.oculus.horizon.api.media;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;
import java.util.UUID;

public class CastNotifRequest extends ApiRequest<CastNotifResponse> {
    public final String mCastServerUrl;
    public final String mSessionId;

    public CastNotifRequest(String str, String str2) {
        Preconditions.checkNotNull(str);
        this.mCastServerUrl = str;
        Preconditions.checkNotNull(str2);
        this.mSessionId = str2;
    }

    public ImmutableMap<String, String> getParams() {
        return ImmutableMap.builder().put("client_mutation_id", UUID.randomUUID().toString()).put("cast_server_url", this.mCastServerUrl).put("session_id", this.mSessionId).build();
    }
}
