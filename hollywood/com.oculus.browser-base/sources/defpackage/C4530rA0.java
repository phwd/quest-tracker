package defpackage;

/* renamed from: rA0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4530rA0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C2617fz0 d;
    public C3337kB0[] e;
    public C0121Bz0[] f;
    public String g = "";
    public String h;
    public T3 i;

    static {
        CC[] ccArr = {new CC(56, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C4530rA0() {
        super(56, 0);
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.i(this.d, 8, true);
        C3337kB0[] kb0Arr = this.e;
        if (kb0Arr != null) {
            C1648aL t = x.t(kb0Arr.length, 16, -1);
            int i2 = 0;
            while (true) {
                C3337kB0[] kb0Arr2 = this.e;
                if (i2 >= kb0Arr2.length) {
                    break;
                }
                t.i(kb0Arr2[i2], (i2 * 8) + 8, false);
                i2++;
            }
        } else {
            x.s(16, true);
        }
        C0121Bz0[] bz0Arr = this.f;
        if (bz0Arr != null) {
            C1648aL t2 = x.t(bz0Arr.length, 24, -1);
            int i3 = 0;
            while (true) {
                C0121Bz0[] bz0Arr2 = this.f;
                if (i3 >= bz0Arr2.length) {
                    break;
                }
                t2.i(bz0Arr2[i3], (i3 * 8) + 8, false);
                i3++;
            }
        } else {
            x.s(24, true);
        }
        x.k(this.g, 32, false);
        x.k(this.h, 40, true);
        x.i(this.i, 48, true);
    }
}
