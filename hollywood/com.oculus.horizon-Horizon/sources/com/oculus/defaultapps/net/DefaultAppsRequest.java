package com.oculus.defaultapps.net;

import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;

public class DefaultAppsRequest extends ApiRequest<DefaultAppsResponse> {
    public ImmutableMap<String, String> mRequestParams;

    public DefaultAppsRequest(String str, boolean z) {
        this.mRequestParams = ImmutableMap.A03("hmd", str, "high_priority", String.valueOf(z));
    }
}
