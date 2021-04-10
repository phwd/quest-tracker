package com.oculus.common.ocauth;

import android.util.Log;
import io.reactivex.functions.Consumer;

/* renamed from: com.oculus.common.ocauth.-$$Lambda$UserAppScopedAuthHelper$36WEoRCLwjeAaBwOZoHGfJTR-U0  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$UserAppScopedAuthHelper$36WEoRCLwjeAaBwOZoHGfJTRU0 implements Consumer {
    public static final /* synthetic */ $$Lambda$UserAppScopedAuthHelper$36WEoRCLwjeAaBwOZoHGfJTRU0 INSTANCE = new $$Lambda$UserAppScopedAuthHelper$36WEoRCLwjeAaBwOZoHGfJTRU0();

    private /* synthetic */ $$Lambda$UserAppScopedAuthHelper$36WEoRCLwjeAaBwOZoHGfJTRU0() {
    }

    public final void accept(Object obj) {
        String str = (String) obj;
        Log.d(UserAppScopedAuthHelper.TAG, "fetchUserInfo - fetched app scoped access token");
    }
}
