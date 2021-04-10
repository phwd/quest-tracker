package com.oculus.horizon.api.cloudstorage2;

import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;
import javax.annotation.concurrent.Immutable;

@Immutable
public class UserCloudFileWildcardsRequest extends ApiRequest<UserCloudFileWildcardsResponse> {
    public final String mAppGroupingId;

    public UserCloudFileWildcardsRequest(String str) {
        this.mAppGroupingId = str;
    }

    public ImmutableMap<String, Object> getParams() {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("app_grouping_id", this.mAppGroupingId);
        return A04.build();
    }
}
