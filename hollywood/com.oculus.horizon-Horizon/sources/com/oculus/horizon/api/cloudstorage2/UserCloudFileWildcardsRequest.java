package com.oculus.horizon.api.cloudstorage2;

import com.google.common.collect.ImmutableMap;
import com.oculus.headlesshorizon.notifications.receivers.NotificationReceiver;
import com.oculus.http.core.base.ApiRequest;
import javax.annotation.concurrent.Immutable;

@Immutable
public class UserCloudFileWildcardsRequest extends ApiRequest<UserCloudFileWildcardsResponse> {
    public final String mAppGroupingId;

    public UserCloudFileWildcardsRequest(String str) {
        this.mAppGroupingId = str;
    }

    public ImmutableMap<String, Object> getParams() {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put(NotificationReceiver.CUSTOM_DATA_APP_GROUP_ID, this.mAppGroupingId);
        return A01.build();
    }
}
