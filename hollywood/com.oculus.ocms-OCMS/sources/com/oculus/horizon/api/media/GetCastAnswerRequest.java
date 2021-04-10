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
        return ImmutableMap.builder().put("id", this.id).build();
    }
}
