package com.oculus.library.net;

import android.content.Context;
import com.google.common.collect.ImmutableMap;
import com.oculus.auth.service.contract.ServiceContract;
import com.oculus.http.core.base.ApiRequest;
import com.oculus.util.device.DeviceUtils;

public class ActiveEntitlementRequest extends ApiRequest<ActiveEntitlementsResponse> {
    public static ImmutableMap<String, Object> getParams(Context context, Float f) {
        return ImmutableMap.builder().put(ServiceContract.EXTRA_MACHINE_ID, DeviceUtils.getDeviceId(context)).put("imageScale", f).build();
    }
}
