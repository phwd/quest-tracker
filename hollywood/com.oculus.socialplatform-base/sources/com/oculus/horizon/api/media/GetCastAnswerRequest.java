package com.oculus.horizon.api.media;

import com.facebook.infer.annotation.Nullsafe;
import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class GetCastAnswerRequest extends ApiRequest<GetCastAnswerResponse> {
    public final String id;

    public GetCastAnswerRequest(String str) {
        this.id = str;
    }

    public ImmutableMap<String, String> getParams() {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("id", this.id);
        return A04.build();
    }
}
