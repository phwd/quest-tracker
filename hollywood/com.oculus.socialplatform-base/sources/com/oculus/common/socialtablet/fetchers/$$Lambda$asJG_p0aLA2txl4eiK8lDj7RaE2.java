package com.oculus.common.socialtablet.fetchers;

import com.oculus.common.fbaccountsmanager.MessengerVrAccountResult;
import java.util.Optional;
import java.util.function.Function;

/* renamed from: com.oculus.common.socialtablet.fetchers.-$$Lambda$asJG_p0aLA2txl4eiK8lDj7R-aE2  reason: invalid class name */
public final /* synthetic */ class $$Lambda$asJG_p0aLA2txl4eiK8lDj7RaE2 implements Function {
    public static final /* synthetic */ $$Lambda$asJG_p0aLA2txl4eiK8lDj7RaE2 INSTANCE = new $$Lambda$asJG_p0aLA2txl4eiK8lDj7RaE2();

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return Optional.ofNullable(((MessengerVrAccountResult) obj).mUserName);
    }
}
