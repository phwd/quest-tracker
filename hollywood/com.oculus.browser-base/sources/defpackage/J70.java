package defpackage;

import android.graphics.RectF;

/* renamed from: J70  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class J70 extends UH0 {
    public static final RH0 A;
    public static final RH0 B;
    public static final RH0 C;
    public static final RH0 D;
    public static final QH0 E;
    public static final QH0 F;
    public static final QH0 G;
    public static final QH0 H;
    public static final QH0 I;

    /* renamed from: J  reason: collision with root package name */
    public static final RH0 f8270J;
    public static final QH0 K;
    public static final RH0 L;
    public static final RH0 M;
    public static final QH0 N;
    public static final TH0 O;
    public static final TH0 P;
    public static final RH0 Q;
    public static final QH0 R;
    public static final QH0 S;
    public static final SH0 T;
    public static final SH0 U;
    public static final SH0 V;
    public static final RH0 W;
    public static final RH0 X;
    public static final KH0[] Y;
    public static float c;
    public static float d;
    public static final SH0 e;
    public static final QH0 f;
    public static final RH0 g;
    public static final RH0 h;
    public static final RH0 i;
    public static final RH0 j;
    public static final RH0 k;
    public static final RH0 l;
    public static final RH0 m;
    public static final RH0 n;
    public static final RH0 o;
    public static final RH0 p;
    public static final RH0 q;
    public static final RH0 r;
    public static final RH0 s;
    public static final RH0 t;
    public static final RH0 u;
    public static final RH0 v;
    public static final RH0 w;
    public static final RH0 x;
    public static final RH0 y;
    public static final RH0 z;

    static {
        SH0 sh0 = new SH0();
        e = sh0;
        QH0 qh0 = new QH0();
        f = qh0;
        RH0 rh0 = new RH0();
        g = rh0;
        RH0 rh02 = new RH0();
        h = rh02;
        RH0 rh03 = new RH0();
        i = rh03;
        RH0 rh04 = new RH0();
        j = rh04;
        RH0 rh05 = new RH0();
        k = rh05;
        RH0 rh06 = new RH0();
        l = rh06;
        RH0 rh07 = new RH0();
        m = rh07;
        RH0 rh08 = new RH0();
        n = rh08;
        RH0 rh09 = new RH0();
        o = rh09;
        RH0 rh010 = new RH0();
        p = rh010;
        RH0 rh011 = new RH0();
        q = rh011;
        RH0 rh012 = new RH0();
        r = rh012;
        RH0 rh013 = new RH0();
        s = rh013;
        RH0 rh014 = new RH0();
        t = rh014;
        RH0 rh015 = new RH0();
        u = rh015;
        RH0 rh016 = new RH0();
        v = rh016;
        RH0 rh017 = new RH0();
        w = rh017;
        RH0 rh018 = new RH0();
        x = rh018;
        RH0 rh019 = new RH0();
        y = rh019;
        RH0 rh020 = new RH0();
        z = rh020;
        RH0 rh021 = new RH0();
        A = rh021;
        RH0 rh022 = new RH0();
        B = rh022;
        RH0 rh023 = new RH0();
        C = rh023;
        RH0 rh024 = new RH0();
        D = rh024;
        QH0 qh02 = new QH0();
        E = qh02;
        QH0 qh03 = new QH0();
        F = qh03;
        QH0 qh04 = new QH0();
        G = qh04;
        QH0 qh05 = new QH0();
        H = qh05;
        QH0 qh06 = new QH0();
        I = qh06;
        RH0 rh025 = new RH0();
        f8270J = rh025;
        QH0 qh07 = new QH0();
        K = qh07;
        RH0 rh026 = new RH0();
        L = rh026;
        RH0 rh027 = new RH0();
        M = rh027;
        QH0 qh08 = new QH0();
        N = qh08;
        TH0 th0 = new TH0(false);
        O = th0;
        TH0 th02 = new TH0(false);
        P = th02;
        RH0 rh028 = new RH0();
        Q = rh028;
        QH0 qh09 = new QH0();
        R = qh09;
        QH0 qh010 = new QH0();
        S = qh010;
        SH0 sh02 = new SH0();
        T = sh02;
        SH0 sh03 = new SH0();
        U = sh03;
        SH0 sh04 = new SH0();
        V = sh04;
        RH0 rh029 = new RH0();
        W = rh029;
        RH0 rh030 = new RH0();
        X = rh030;
        Y = new KH0[]{sh0, qh0, rh0, rh02, rh03, rh04, rh05, rh06, rh07, rh08, rh09, rh010, rh011, rh012, rh013, rh014, rh015, rh016, rh017, rh018, rh019, rh020, rh021, rh022, rh023, rh024, qh02, qh03, qh04, qh05, qh06, rh025, qh07, rh026, rh027, qh08, th0, th02, rh028, qh09, qh010, sh02, sh03, sh04, rh029, rh030};
    }

    public J70(int i2, boolean z2, int i3, int i4, boolean z3, boolean z4) {
        super(Y);
        l(e, i2);
        j(f, z2);
        m(O, new RectF());
        m(P, new RectF());
        l(T, -1);
        l(U, -855310);
        l(V, -1);
        k(W, 1.0f);
        D(i3, i4, z3, z4);
    }

    public float A() {
        return e(z);
    }

    public float B() {
        return e(l);
    }

    public float C() {
        return e(m);
    }

    public void D(int i2, int i3, boolean z2, boolean z3) {
        k(t, 1.0f);
        k(u, 1.0f);
        k(D, 1.0f);
        k(v, 1.0f);
        k(w, z2 ? 1.0f : 0.0f);
        k(x, 1.0f);
        k(p, 0.0f);
        k(q, 0.0f);
        k(r, Float.MAX_VALUE);
        k(s, Float.MAX_VALUE);
        k(g, 1.0f);
        k(h, 0.0f);
        k(i, 0.0f);
        j(E, true);
        k(l, 0.0f);
        k(m, 0.0f);
        k(n, 0.0f);
        k(o, 0.0f);
        k(C, 0.0f);
        k(Q, 1.0f);
        j(R, z3);
        j(G, true);
        j(H, false);
        j(I, false);
        k(f8270J, 1.0f);
        j(K, false);
        k(L, 0.0f);
        k(M, 1.0f);
        float f2 = (float) i2;
        k(y, d * f2);
        float f3 = (float) i3;
        k(z, d * f3);
        k(A, f2 * d);
        k(B, f3 * d);
        j(S, false);
    }

    public boolean E() {
        return h(f);
    }

    public boolean F() {
        return h(E);
    }

    public void G(float f2, float f3) {
        k(r, f2);
        k(s, f3);
    }

    public boolean H() {
        return h(H);
    }

    public float n() {
        return e(t);
    }

    public float o() {
        return Math.min(e(s), v());
    }

    public float p() {
        return Math.min(e(r), w());
    }

    public int q() {
        return f(e);
    }

    public float r() {
        return e(B);
    }

    public float s() {
        return Math.min(e(z), e(B));
    }

    public float t() {
        return Math.min(e(y), e(A));
    }

    public String toString() {
        return Integer.toString(q());
    }

    public float u() {
        return e(g);
    }

    public float v() {
        return e(g) * s();
    }

    public float w() {
        return e(g) * t();
    }

    public float x() {
        return e(h);
    }

    public float y() {
        return e(i);
    }

    public float z() {
        return e(f8270J);
    }
}
