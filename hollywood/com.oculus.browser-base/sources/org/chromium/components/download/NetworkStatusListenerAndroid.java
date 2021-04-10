package org.chromium.components.download;

import J.N;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class NetworkStatusListenerAndroid implements AbstractC0524In0 {
    public static C1011Qn0 F = new C1011Qn0();
    public long G;
    public final C0646Kn0 H = new C0646Kn0(this, new C5582xL0());

    public NetworkStatusListenerAndroid(long j) {
        this.G = j;
    }

    public static NetworkStatusListenerAndroid create(long j) {
        return new NetworkStatusListenerAndroid(j);
    }

    @Override // defpackage.AbstractC0524In0
    public void a(int i) {
        long j = this.G;
        if (j != 0) {
            N.M9CWqWuv(j, this, i);
        }
    }

    @Override // defpackage.AbstractC0524In0
    public void b(long j, int i) {
    }

    @Override // defpackage.AbstractC0524In0
    public void c(int i) {
    }

    public final void clearNativePtr() {
        this.H.h();
        this.G = 0;
    }

    @Override // defpackage.AbstractC0524In0
    public void d(long[] jArr) {
    }

    public final int getCurrentConnectionType() {
        return this.H.f().b();
    }

    @Override // defpackage.AbstractC0524In0
    public void k(long j) {
    }

    @Override // defpackage.AbstractC0524In0
    public void l(long j) {
    }
}
