package defpackage;

/* renamed from: rI0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4554rI0 extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C5235vI0 d;
    public C5745yI0 e;
    public byte[] f;
    public C4895tI0[] g;
    public C0087Bi1 h;
    public C4725sI0[] i;
    public C0064Bb j;
    public int k;
    public C1891bl l;
    public boolean m;
    public boolean n;
    public boolean o;
    public int p;
    public boolean q;
    public String r;
    public boolean s;
    public int t;

    static {
        CC[] ccArr = {new CC(96, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C4554rI0(int i2) {
        super(96, i2);
    }

    /* JADX INFO: finally extract failed */
    public static C4554rI0 d(C4709sD sDVar) {
        C1891bl blVar = null;
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C4554rI0 ri0 = new C4554rI0(sDVar.c(b).b);
            boolean z = false;
            ri0.d = C5235vI0.d(sDVar.s(8, false));
            ri0.e = C5745yI0.d(sDVar.s(16, false));
            ri0.f = sDVar.e(24, 0, -1);
            C4709sD s2 = sDVar.s(32, false);
            CC i2 = s2.i(-1);
            ri0.g = new C4895tI0[i2.b];
            for (int i3 = 0; i3 < i2.b; i3++) {
                ri0.g[i3] = C4895tI0.d(AbstractC2531fV.n(i3, 8, 8, s2, false));
            }
            ri0.h = C0087Bi1.d(sDVar.s(40, true));
            C4709sD s3 = sDVar.s(48, false);
            CC i4 = s3.i(-1);
            ri0.i = new C4725sI0[i4.b];
            for (int i5 = 0; i5 < i4.b; i5++) {
                ri0.i[i5] = C4725sI0.d(AbstractC2531fV.n(i5, 8, 8, s3, false));
            }
            ri0.j = C0064Bb.d(sDVar.s(56, true));
            int n2 = sDVar.n(64);
            ri0.k = n2;
            if (n2 >= 0 && n2 <= 3) {
                ri0.m = sDVar.d(68, 0);
                ri0.n = sDVar.d(68, 1);
                ri0.o = sDVar.d(68, 2);
                ri0.q = sDVar.d(68, 3);
                ri0.s = sDVar.d(68, 4);
                C4709sD s4 = sDVar.s(72, true);
                CC[] ccArr = C1891bl.b;
                if (s4 != null) {
                    s4.b();
                    try {
                        C1891bl blVar2 = new C1891bl(s4.c(C1891bl.b).b);
                        blVar2.d = s4.e(8, 0, -1);
                        blVar2.e = s4.e(16, 0, 65);
                        s4.a();
                        blVar = blVar2;
                    } catch (Throwable th) {
                        s4.a();
                        throw th;
                    }
                }
                ri0.l = blVar;
                int n3 = sDVar.n(80);
                ri0.p = n3;
                if (n3 >= 0 && n3 <= 3) {
                    int n4 = sDVar.n(84);
                    ri0.t = n4;
                    if (n4 >= 0 && n4 <= 2) {
                        z = true;
                    }
                    if (z) {
                        ri0.r = sDVar.v(88, true);
                        sDVar.a();
                        return ri0;
                    }
                    throw new C4200pE("Invalid enum value.");
                }
                throw new C4200pE("Invalid enum value.");
            }
            throw new C4200pE("Invalid enum value.");
        } catch (Throwable th2) {
            sDVar.a();
            throw th2;
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        x.i(this.d, 8, false);
        x.i(this.e, 16, false);
        x.n(this.f, 24, 0, -1);
        C4895tI0[] ti0Arr = this.g;
        if (ti0Arr != null) {
            C1648aL t2 = x.t(ti0Arr.length, 32, -1);
            int i2 = 0;
            while (true) {
                C4895tI0[] ti0Arr2 = this.g;
                if (i2 >= ti0Arr2.length) {
                    break;
                }
                t2.i(ti0Arr2[i2], (i2 * 8) + 8, false);
                i2++;
            }
        } else {
            x.s(32, false);
        }
        x.i(this.h, 40, true);
        C4725sI0[] si0Arr = this.i;
        if (si0Arr != null) {
            C1648aL t3 = x.t(si0Arr.length, 48, -1);
            int i3 = 0;
            while (true) {
                C4725sI0[] si0Arr2 = this.i;
                if (i3 >= si0Arr2.length) {
                    break;
                }
                t3.i(si0Arr2[i3], (i3 * 8) + 8, false);
                i3++;
            }
        } else {
            x.s(48, false);
        }
        x.i(this.j, 56, true);
        x.c(this.k, 64);
        x.m(this.m, 68, 0);
        x.m(this.n, 68, 1);
        x.m(this.o, 68, 2);
        x.m(this.q, 68, 3);
        x.m(this.s, 68, 4);
        x.i(this.l, 72, true);
        x.c(this.p, 80);
        x.c(this.t, 84);
        x.k(this.r, 88, true);
    }
}
