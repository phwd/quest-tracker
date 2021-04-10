package com.oculus.horizon.api.library;

import android.content.Context;
import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;
import com.oculus.util.device.DeviceUtils;

public class ActiveEntitlementRequest extends ApiRequest<ActiveEntitlementsResponse> {
    public static ImmutableMap<String, String> getParams(Context context) {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("machine_id", DeviceUtils.A03(context));
        return A01.build();
    }
}
