package com.oculus.common.fbaccountsmanager;

import java.util.Optional;
import java.util.function.Function;

/* renamed from: com.oculus.common.fbaccountsmanager.-$$Lambda$31ChSGHsypatjHzKux9psUxBd8s2  reason: invalid class name */
public final /* synthetic */ class $$Lambda$31ChSGHsypatjHzKux9psUxBd8s2 implements Function {
    public static final /* synthetic */ $$Lambda$31ChSGHsypatjHzKux9psUxBd8s2 INSTANCE = new $$Lambda$31ChSGHsypatjHzKux9psUxBd8s2();

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return Optional.ofNullable(((MessengerVrAccountResult) obj).mUserId);
    }
}
