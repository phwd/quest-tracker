package com.oculus.horizon.api.cloudstorage2;

import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;
import javax.annotation.concurrent.Immutable;

@Immutable
public class UserCloudFilesRequest extends ApiRequest<UserCloudFilesResponse> {
    private final String mAppGroupingId;

    public UserCloudFilesRequest(String str) {
        this.mAppGroupingId = str;
    }

    public ImmutableMap<String, Object> getParams() {
        return ImmutableMap.builder().put("app_grouping_id", this.mAppGroupingId).build();
    }
}
