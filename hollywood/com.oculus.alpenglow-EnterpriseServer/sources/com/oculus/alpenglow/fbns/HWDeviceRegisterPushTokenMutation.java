package com.oculus.alpenglow.fbns;

import X.AbstractC03090bY;
import X.AnonymousClass0P9;
import com.facebook.graphql.modelutil.GeneratedGraphQL;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.alpenglow.graphql.calls.HWPushTokenRegisterRequest;

@GeneratedGraphQL
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class HWDeviceRegisterPushTokenMutation {

    @GeneratedGraphQL
    public interface Builder extends BuilderForRequest, AnonymousClass0P9<AbstractC03090bY<HWDeviceRegisterPushTokenResponse>, HWDeviceRegisterPushTokenResponse> {
    }

    @GeneratedGraphQL
    public interface BuilderForRequest {
        Builder A86(HWPushTokenRegisterRequest hWPushTokenRegisterRequest);
    }
}
