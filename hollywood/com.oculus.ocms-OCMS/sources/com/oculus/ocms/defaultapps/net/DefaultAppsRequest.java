package com.oculus.ocms.defaultapps.net;

import com.facebook.infer.annotation.Nullsafe;
import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DefaultAppsRequest extends ApiRequest<DefaultAppsResponse> {
    private ImmutableMap<String, String> mRequestParams;

    public DefaultAppsRequest(String str, boolean z) {
        this.mRequestParams = ImmutableMap.of("hmd", str, "high_priority", String.valueOf(z));
    }

    public ImmutableMap<String, String> getParams() {
        return this.mRequestParams;
    }
}
