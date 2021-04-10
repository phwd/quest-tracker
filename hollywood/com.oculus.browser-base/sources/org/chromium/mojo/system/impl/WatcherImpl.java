package org.chromium.mojo.system.impl;

import J.N;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WatcherImpl {

    /* renamed from: a  reason: collision with root package name */
    public long f10997a = N.MXGgOw9k(this);
    public Cw1 b;

    public int a(PW pw, RA ra, Cw1 cw1) {
        long j = this.f10997a;
        if (j == 0 || !(pw instanceof QW)) {
            return 3;
        }
        int MOmBVeTt = N.MOmBVeTt(this, j, ((QW) pw).F, ra.f9606a);
        if (MOmBVeTt == 0) {
            this.b = cw1;
        }
        return MOmBVeTt;
    }

    public final void onHandleReady(int i) {
        this.b.a(i);
    }
}
