package defpackage;

import J.N;
import java.nio.ByteBuffer;
import org.chromium.mojo.system.ResultAnd;
import org.chromium.mojo.system.impl.WatcherImpl;

/* renamed from: gy  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2784gy implements AbstractC3255jk0, RW {
    public final C2613fy F = new C2613fy(this, null);
    public final AbstractC1552Zj0 G;
    public final WatcherImpl H;
    public AbstractC3255jk0 I;

    /* renamed from: J  reason: collision with root package name */
    public AbstractC0543Ix f10037J;

    public C2784gy(AbstractC1552Zj0 zj0) {
        WatcherImpl a2 = AbstractC5802yh.a(zj0);
        this.G = zj0;
        this.H = a2;
    }

    public static ResultAnd h0(AbstractC1552Zj0 zj0, AbstractC3255jk0 jk0) {
        ResultAnd W = zj0.W(C1369Wj0.c);
        int i = W.f10995a;
        if (i != 0) {
            return new ResultAnd(i, Boolean.FALSE);
        }
        C1430Xj0 xj0 = (C1430Xj0) W.b;
        if (jk0 == null) {
            return new ResultAnd(i, Boolean.FALSE);
        }
        try {
            return new ResultAnd(W.f10995a, Boolean.valueOf(jk0.b(new C2740gj0(ByteBuffer.wrap(xj0.f9230a), xj0.c))));
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public final void Y() {
        WatcherImpl watcherImpl = this.H;
        long j = watcherImpl.f10997a;
        if (j != 0) {
            watcherImpl.b = null;
            N.MPTT407x(watcherImpl, j);
        }
        WatcherImpl watcherImpl2 = this.H;
        long j2 = watcherImpl2.f10997a;
        if (j2 != 0) {
            N.Mi32vqDA(watcherImpl2, j2);
            watcherImpl2.f10997a = 0;
        }
    }

    @Override // defpackage.AbstractC3255jk0
    public boolean b(C2740gj0 gj0) {
        try {
            this.G.c(gj0.f10015a, gj0.b, C1491Yj0.c);
            return true;
        } catch (C5475wl0 e) {
            f0(e);
            return false;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, defpackage.AbstractC3255jk0
    public void close() {
        Y();
        this.G.close();
        AbstractC3255jk0 jk0 = this.I;
        if (jk0 != null) {
            this.I = null;
            jk0.close();
        }
    }

    public final void f0(C5475wl0 wl0) {
        close();
        AbstractC0543Ix ix = this.f10037J;
        if (ix != null) {
            try {
                ix.Y(wl0);
            } catch (RuntimeException e) {
                throw e;
            }
        }
    }

    /* renamed from: g0 */
    public AbstractC1552Zj0 x() {
        Y();
        AbstractC1552Zj0 N = this.G.N();
        AbstractC3255jk0 jk0 = this.I;
        if (jk0 != null) {
            jk0.close();
        }
        return N;
    }

    public C2784gy(AbstractC1552Zj0 zj0, WatcherImpl watcherImpl) {
        this.G = zj0;
        this.H = watcherImpl;
    }
}
