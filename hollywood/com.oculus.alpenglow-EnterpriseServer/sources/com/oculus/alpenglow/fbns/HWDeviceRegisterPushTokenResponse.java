package com.oculus.alpenglow.fbns;

import X.AbstractC01940Ou;
import com.facebook.graphql.modelutil.GeneratedGraphQL;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@GeneratedGraphQL
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public interface HWDeviceRegisterPushTokenResponse extends AbstractC01940Ou {

    @GeneratedGraphQL
    @ThreadSafe
    public interface HwDeviceRegisterPushToken extends AbstractC01940Ou {
        @Nullable
        String A4I();
    }

    @Nullable
    HwDeviceRegisterPushToken A3h();
}
