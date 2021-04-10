package com.oculus.horizon.social.request;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import javax.annotation.concurrent.Immutable;

@Immutable
public class GetQueuedRemoteLaunchesParams {
    public final String mDeviceSerial;

    public String toString() {
        return GraphQLParamsHelper.GSON_CONVERTER.A06(ImmutableMap.A05("device_serial", this.mDeviceSerial));
    }

    public GetQueuedRemoteLaunchesParams(String str) {
        this.mDeviceSerial = str;
    }
}
