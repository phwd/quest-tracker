package com.facebook.tigon.iface;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class RedirectRequestInfo {
    private boolean mRedirectEnabled;

    @DoNotStrip
    public RedirectRequestInfo(boolean z) {
        this.mRedirectEnabled = z;
    }

    public boolean redirectEnabled() {
        return this.mRedirectEnabled;
    }
}
