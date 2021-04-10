package defpackage;

import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;

/* renamed from: ah  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1699ah implements AbstractC1420Xg {
    public final C1542Zg F;
    public C2562fh G;
    public C3245jh H;
    public boolean I = false;

    /* renamed from: J  reason: collision with root package name */
    public boolean f9442J = true;

    public C1699ah(C1542Zg zg) {
        this.F = zg;
    }

    @Override // defpackage.AbstractC0543Ix
    public void Y(C5475wl0 wl0) {
        g0();
    }

    @Override // java.io.Closeable, defpackage.AbstractC3313k30, java.lang.AutoCloseable
    public void close() {
        g0();
    }

    @Override // defpackage.AbstractC1420Xg
    public void e0(C2562fh fhVar) {
        if (this.G != null) {
            AbstractC1220Ua0.a("BatteryMonitorImpl", "Overlapped call to queryNextStatus!", new Object[0]);
            g0();
            return;
        }
        this.G = fhVar;
        if (this.I) {
            fhVar.a(this.H);
            this.G = null;
            this.I = false;
        }
    }

    public void f0(C3245jh jhVar) {
        this.H = jhVar;
        this.I = true;
        C2562fh fhVar = this.G;
        if (fhVar != null) {
            fhVar.a(jhVar);
            this.G = null;
            this.I = false;
        }
    }

    public final void g0() {
        if (this.f9442J) {
            C1542Zg zg = this.F;
            Objects.requireNonNull(zg);
            Object obj = ThreadUtils.f10596a;
            zg.b.remove(this);
            if (zg.b.isEmpty()) {
                C3929nh nhVar = zg.f9358a;
                if (nhVar.e) {
                    ContextUtils.getApplicationContext().unregisterReceiver(nhVar.c);
                    nhVar.e = false;
                }
                zg.c = false;
            }
            this.f9442J = false;
        }
    }
}
