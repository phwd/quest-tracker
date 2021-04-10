package com.oculus.horizon.api.library;

import android.content.Context;
import com.google.common.collect.ImmutableMap;
import com.oculus.auth.service.contract.ServiceContract;
import com.oculus.http.core.base.ApiRequest;
import com.oculus.util.device.DeviceUtils;

public class ActiveEntitlementRequest extends ApiRequest<ActiveEntitlementsResponse> {
    public static ImmutableMap<String, String> getParams(Context context) {
        return ImmutableMap.builder().put(ServiceContract.EXTRA_MACHINE_ID, DeviceUtils.getDeviceId(context)).build();
    }
}
