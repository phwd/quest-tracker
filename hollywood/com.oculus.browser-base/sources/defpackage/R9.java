package defpackage;

import J.N;

/* renamed from: R9  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class R9 implements AbstractC0543Ix {
    public long F = 0;
    public C2784gy G;
    public boolean H;

    public R9(C1889bk0 bk0) {
        C5318vp1 vp1;
        boolean z = false;
        if (!(!bk0.d.a() || (vp1 = bk0.e) == null || ((vp1.e == 0 && vp1.d == 0) || bk0.f == 0))) {
            z = true;
        }
        if (!z) {
            b();
            return;
        }
        int h = bk0.d.h();
        C5318vp1 vp12 = bk0.e;
        this.F = N.MsjrrfZt(h, vp12.e, vp12.d, bk0.f);
        bk0.d.close();
        C5318vp1 vp13 = bk0.e;
        if (vp13 != null) {
            vp13.e = 0;
            vp13.d = 0;
        }
        bk0.f = 0;
    }

    @Override // defpackage.AbstractC0543Ix
    public void Y(C5475wl0 wl0) {
        this.H = true;
    }

    public void a() {
        int h = this.G.x().h();
        if (this.H) {
            N.MpISG4bN(this.F);
        } else {
            N.MylsTJ$B(this.F, h);
        }
        this.G = null;
    }

    public final void b() {
        this.F = 0;
        this.G = null;
        this.H = false;
    }

    public final void finalize() {
        try {
            long j = this.F;
            if (j != 0) {
                N.MgnCh9Wp(j);
            }
        } finally {
            super.finalize();
        }
    }

    public R9(long j) {
        this.F = j;
    }
}
