package com.oculus.common.fbaccountsmanager;

import java.util.function.Function;

/* renamed from: com.oculus.common.fbaccountsmanager.-$$Lambda$DDuCzW9wSyB-0-40FvvEOcpJysE  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$DDuCzW9wSyB040FvvEOcpJysE implements Function {
    public static final /* synthetic */ $$Lambda$DDuCzW9wSyB040FvvEOcpJysE INSTANCE = new $$Lambda$DDuCzW9wSyB040FvvEOcpJysE();

    private /* synthetic */ $$Lambda$DDuCzW9wSyB040FvvEOcpJysE() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((MessengerVrAccountResult) obj).getMessengerIsAuthenticated();
    }
}
