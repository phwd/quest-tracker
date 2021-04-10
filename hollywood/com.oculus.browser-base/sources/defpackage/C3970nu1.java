package defpackage;

/* renamed from: nu1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3970nu1 {

    /* renamed from: a  reason: collision with root package name */
    public final BW0 f10519a = new BW0();
    public final C4083ob0 b = new C4083ob0();

    public void a(XK0 xk0) {
        C3799mu1 mu1 = (C3799mu1) this.f10519a.getOrDefault(xk0, null);
        if (mu1 == null) {
            mu1 = C3799mu1.a();
            this.f10519a.put(xk0, mu1);
        }
        mu1.b |= 1;
    }

    public void b(XK0 xk0, CK0 ck0) {
        C3799mu1 mu1 = (C3799mu1) this.f10519a.getOrDefault(xk0, null);
        if (mu1 == null) {
            mu1 = C3799mu1.a();
            this.f10519a.put(xk0, mu1);
        }
        mu1.d = ck0;
        mu1.b |= 8;
    }

    public void c(XK0 xk0, CK0 ck0) {
        C3799mu1 mu1 = (C3799mu1) this.f10519a.getOrDefault(xk0, null);
        if (mu1 == null) {
            mu1 = C3799mu1.a();
            this.f10519a.put(xk0, mu1);
        }
        mu1.c = ck0;
        mu1.b |= 4;
    }

    public boolean d(XK0 xk0) {
        C3799mu1 mu1 = (C3799mu1) this.f10519a.getOrDefault(xk0, null);
        if (mu1 == null || (mu1.b & 1) == 0) {
            return false;
        }
        return true;
    }

    public final CK0 e(XK0 xk0, int i) {
        C3799mu1 mu1;
        CK0 ck0;
        int e = this.f10519a.e(xk0);
        if (e >= 0 && (mu1 = (C3799mu1) this.f10519a.k(e)) != null) {
            int i2 = mu1.b;
            if ((i2 & i) != 0) {
                int i3 = (~i) & i2;
                mu1.b = i3;
                if (i == 4) {
                    ck0 = mu1.c;
                } else if (i == 8) {
                    ck0 = mu1.d;
                } else {
                    throw new IllegalArgumentException("Must provide flag PRE or POST");
                }
                if ((i3 & 12) == 0) {
                    this.f10519a.i(e);
                    C3799mu1.b(mu1);
                }
                return ck0;
            }
        }
        return null;
    }

    public void f(XK0 xk0) {
        C3799mu1 mu1 = (C3799mu1) this.f10519a.getOrDefault(xk0, null);
        if (mu1 != null) {
            mu1.b &= -2;
        }
    }

    public void g(XK0 xk0) {
        int k = this.b.k() - 1;
        while (true) {
            if (k < 0) {
                break;
            } else if (xk0 == this.b.l(k)) {
                C4083ob0 ob0 = this.b;
                Object[] objArr = ob0.I;
                Object obj = objArr[k];
                Object obj2 = C4083ob0.F;
                if (obj != obj2) {
                    objArr[k] = obj2;
                    ob0.G = true;
                }
            } else {
                k--;
            }
        }
        C3799mu1 mu1 = (C3799mu1) this.f10519a.remove(xk0);
        if (mu1 != null) {
            C3799mu1.b(mu1);
        }
    }
}
