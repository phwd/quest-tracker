package com.oculus.license;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

public class LicenseInfoRequest {
    public final String appId;

    public LicenseInfoRequest(String str) {
        this.appId = str;
    }

    public String toJsonParam() {
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        builder.put("app_id", this.appId);
        return new Gson().toJson(builder.build());
    }
}
