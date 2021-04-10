package com.oculus.horizon.api.library;

import android.content.Context;
import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;
import com.oculus.util.device.DeviceUtils;

public class ActiveEntitlementRequest extends ApiRequest<ActiveEntitlementsResponse> {
    public static ImmutableMap<String, String> getParams(Context context) {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("machine_id", DeviceUtils.getDeviceId(context));
        return A04.build();
    }
}
