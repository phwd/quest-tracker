package org.chromium.content.browser;

import J.N;
import android.os.Process;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BackgroundSyncNetworkObserver implements AbstractC0524In0 {
    public static BackgroundSyncNetworkObserver F;
    public C0646Kn0 G;
    public List H = new ArrayList();
    public int I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f10908J;

    public BackgroundSyncNetworkObserver() {
        Object obj = ThreadUtils.f10596a;
    }

    public static BackgroundSyncNetworkObserver createObserver(long j) {
        Object obj = ThreadUtils.f10596a;
        if (F == null) {
            F = new BackgroundSyncNetworkObserver();
        }
        BackgroundSyncNetworkObserver backgroundSyncNetworkObserver = F;
        Objects.requireNonNull(backgroundSyncNetworkObserver);
        if (!(AbstractC3153j7.a(ContextUtils.getApplicationContext(), "android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) == 0)) {
            AbstractC3100ip1.f10165a.a("BackgroundSync.NetworkObserver.HasPermission", false);
        } else {
            if (backgroundSyncNetworkObserver.G == null) {
                backgroundSyncNetworkObserver.G = new C0646Kn0(backgroundSyncNetworkObserver, new C5582xL0());
                AbstractC3100ip1.f10165a.a("BackgroundSync.NetworkObserver.HasPermission", true);
            }
            backgroundSyncNetworkObserver.H.add(Long.valueOf(j));
            N.MJIG3QvD(j, backgroundSyncNetworkObserver, backgroundSyncNetworkObserver.G.f().b());
        }
        return F;
    }

    @Override // defpackage.AbstractC0524In0
    public void a(int i) {
        Object obj = ThreadUtils.f10596a;
        e(i);
    }

    @Override // defpackage.AbstractC0524In0
    public void b(long j, int i) {
        Object obj = ThreadUtils.f10596a;
        e(this.G.f().b());
    }

    @Override // defpackage.AbstractC0524In0
    public void c(int i) {
    }

    @Override // defpackage.AbstractC0524In0
    public void d(long[] jArr) {
    }

    public final void e(int i) {
        if (!this.f10908J || i != this.I) {
            this.f10908J = true;
            this.I = i;
            for (Long l : this.H) {
                N.MJIG3QvD(l.longValue(), this, i);
            }
        }
    }

    @Override // defpackage.AbstractC0524In0
    public void k(long j) {
        Object obj = ThreadUtils.f10596a;
        e(this.G.f().b());
    }

    @Override // defpackage.AbstractC0524In0
    public void l(long j) {
    }

    public final void removeObserver(long j) {
        C0646Kn0 kn0;
        Object obj = ThreadUtils.f10596a;
        this.H.remove(Long.valueOf(j));
        if (this.H.size() == 0 && (kn0 = this.G) != null) {
            kn0.d();
            this.G = null;
        }
    }
}
