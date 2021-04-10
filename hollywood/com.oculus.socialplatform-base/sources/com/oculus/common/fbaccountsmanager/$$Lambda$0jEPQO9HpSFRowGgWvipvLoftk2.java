package com.oculus.common.fbaccountsmanager;

import java.util.Optional;
import java.util.function.Function;

/* renamed from: com.oculus.common.fbaccountsmanager.-$$Lambda$0jEPQO9HpSF-RowGgWvipvLoftk2  reason: invalid class name */
public final /* synthetic */ class $$Lambda$0jEPQO9HpSFRowGgWvipvLoftk2 implements Function {
    public static final /* synthetic */ $$Lambda$0jEPQO9HpSFRowGgWvipvLoftk2 INSTANCE = new $$Lambda$0jEPQO9HpSFRowGgWvipvLoftk2();

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return Optional.ofNullable(((MessengerVrAccountResult) obj).mHasSeenNewUserAuthenticationDialog);
    }
}
