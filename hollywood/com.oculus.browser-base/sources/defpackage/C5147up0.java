package defpackage;

import android.content.Context;

/* renamed from: up0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5147up0 extends AbstractC2882hZ0 {
    public boolean M;
    public boolean N;
    public long O;
    public int P;

    public C5147up0(Context context, AbstractC5780yZ0 yz0) {
        super(context, yz0);
    }

    @Override // defpackage.AbstractC2882hZ0
    public float A() {
        return 0.0f;
    }

    @Override // defpackage.AbstractC2882hZ0
    public void G(float f, float f2, int i) {
        super.G(f, f2, i);
        int a0 = a0();
        this.e = i(0);
        c0(a0);
        d0();
    }

    @Override // defpackage.AbstractC2882hZ0
    public void H(long j) {
        this.n = 0;
        if (this.z == 10) {
            W(j);
        }
        this.u = null;
        e(j, false);
        this.O = j;
        int a0 = a0();
        this.P = a0;
        this.g.e.r = a0;
    }

    @Override // defpackage.AbstractC2882hZ0
    public void I(long j, float f, float f2) {
    }

    @Override // defpackage.AbstractC2882hZ0
    public void J(long j, float f, float f2, float f3, float f4, boolean z) {
    }

    @Override // defpackage.AbstractC2882hZ0
    public void L() {
        N81 n81 = this.c;
        if (n81 != null) {
            c0(n81.index());
        }
    }

    @Override // defpackage.AbstractC2882hZ0
    public float N(float f) {
        return f;
    }

    @Override // defpackage.AbstractC2882hZ0
    public float O(float f) {
        return f;
    }

    @Override // defpackage.AbstractC2882hZ0
    public boolean Q() {
        return false;
    }

    @Override // defpackage.AbstractC2882hZ0
    public boolean R() {
        return false;
    }

    @Override // defpackage.AbstractC2882hZ0
    public boolean S() {
        return false;
    }

    @Override // defpackage.AbstractC2882hZ0
    public void T(long j) {
        int i;
        if (this.g.d()) {
            int i2 = this.e;
            float f = (((float) ((-this.P) * i2)) - this.r) / ((float) i2);
            if (j >= this.O + 250 || Math.abs(f) <= 0.05f) {
                i = a0();
            } else {
                i = this.P + ((int) Math.signum(f));
            }
            int i3 = (-i) * this.e;
            this.g.e.e((int) this.q, i3, j);
            P((float) i3, false);
            this.E.M();
        }
    }

    @Override // defpackage.AbstractC2882hZ0
    public boolean a() {
        return false;
    }

    public int a0() {
        return Math.round((-this.r) / ((float) this.e));
    }

    public final int b0() {
        int i = 0;
        if (this.f == null) {
            return 0;
        }
        int i2 = 0;
        while (true) {
            IZ0[] iz0Arr = this.f;
            if (i >= iz0Arr.length) {
                return iz0Arr.length - i2;
            }
            if (iz0Arr[i].w) {
                i2++;
            }
            i++;
        }
    }

    public final void c0(int i) {
        if (this.f != null) {
            int i2 = 0;
            while (true) {
                IZ0[] iz0Arr = this.f;
                if (i2 >= iz0Arr.length) {
                    break;
                }
                iz0Arr[i2].k = (float) (this.e * i2);
                i2++;
            }
        }
        float f = (float) ((-i) * this.e);
        this.r = f;
        P(f, false);
    }

    public final void d0() {
        AZ0 az0 = this.g;
        az0.d.q = 0.6f;
        az0.e.q = 0.6f;
        this.g.e.t = Math.round((x() * 0.54f) + 25.0f);
    }

    @Override // defpackage.AbstractC2882hZ0
    public int h() {
        return a0();
    }

    @Override // defpackage.AbstractC2882hZ0
    public int i(int i) {
        return Math.round((w() * x()) + 25.0f);
    }

    @Override // defpackage.AbstractC2882hZ0
    public void j() {
        int a0 = a0();
        int i = 0;
        while (true) {
            IZ0[] iz0Arr = this.f;
            if (i < iz0Arr.length) {
                J70 j70 = iz0Arr[i].C;
                if (i < a0 - 1 || i > a0 + 2) {
                    j70.j(J70.E, false);
                } else {
                    j70.j(J70.E, true);
                }
                i++;
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractC2882hZ0
    public void k(Context context) {
        super.k(context);
        d0();
    }

    @Override // defpackage.AbstractC2882hZ0
    public boolean m(float f, boolean z) {
        return false;
    }

    @Override // defpackage.AbstractC2882hZ0
    public void n(long j) {
        super.n(j);
        this.M = false;
    }

    @Override // defpackage.AbstractC2882hZ0
    public float u() {
        if (b0() > 1) {
            return this.E.G;
        }
        return this.E.G * 0.675f;
    }

    @Override // defpackage.AbstractC2882hZ0
    public float v(boolean z) {
        if (this.M) {
            return -3.4028235E38f;
        }
        IZ0[] iz0Arr = this.f;
        if (iz0Arr == null) {
            return 0.0f;
        }
        for (int length = iz0Arr.length - 1; length >= 0; length--) {
            IZ0[] iz0Arr2 = this.f;
            if (!(iz0Arr2[length].w || iz0Arr2[length].k == 0.0f)) {
                return -iz0Arr2[length].k;
            }
        }
        return 0.0f;
    }

    @Override // defpackage.AbstractC2882hZ0
    public float w() {
        return b0() > 1 ? 0.54f : 0.8f;
    }

    @Override // defpackage.AbstractC2882hZ0
    public float y() {
        return 0.0f;
    }

    @Override // defpackage.AbstractC2882hZ0
    public float z() {
        return 0.0f;
    }
}
