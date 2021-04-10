package com.oculus.horizon.social.request;

import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.api.graphql.GraphQLParamsHelper;
import com.oculus.horizon.remotelaunchlogger.RemoteLaunchLogger;
import javax.annotation.concurrent.Immutable;

@Immutable
public class GetQueuedRemoteLaunchesParams {
    public final String mDeviceSerial;

    public final String toString() {
        return GraphQLParamsHelper.GSON_CONVERTER.A06(ImmutableMap.A02(RemoteLaunchLogger.KEY_DEVICE_SERIAL, this.mDeviceSerial));
    }

    public GetQueuedRemoteLaunchesParams(String str) {
        this.mDeviceSerial = str;
    }
}
