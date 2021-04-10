package com.oculus.common.fbaccountsmanager;

import java.util.Optional;
import java.util.function.Function;

/* renamed from: com.oculus.common.fbaccountsmanager.-$$Lambda$DDuCzW9wSyB-0-40FvvEOcpJysE2  reason: invalid class name */
public final /* synthetic */ class $$Lambda$DDuCzW9wSyB040FvvEOcpJysE2 implements Function {
    public static final /* synthetic */ $$Lambda$DDuCzW9wSyB040FvvEOcpJysE2 INSTANCE = new $$Lambda$DDuCzW9wSyB040FvvEOcpJysE2();

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return Optional.ofNullable(((MessengerVrAccountResult) obj).mMessengerIsAuthenticated);
    }
}
