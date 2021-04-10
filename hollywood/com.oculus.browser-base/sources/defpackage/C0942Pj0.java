package defpackage;

/* renamed from: Pj0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0942Pj0 {

    /* renamed from: a  reason: collision with root package name */
    public static final CC f8710a = new CC(24, 0);
    public static final CC b = new CC(32, 1);
    public final CC c;
    public final int d;
    public final int e;
    public long f;

    public C0942Pj0(int i) {
        this.c = f8710a;
        this.d = i;
        this.e = 0;
        this.f = 0;
    }

    public static boolean b(int i) {
        return (i & 3) != 0;
    }

    public boolean a(int i) {
        return (this.e & i) == i;
    }

    public boolean c(int i) {
        return (this.e & 7) == i;
    }

    public boolean d(int i, int i2) {
        if (this.d == i) {
            if ((this.e & 7) == i2) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || C0942Pj0.class != obj.getClass()) {
            return false;
        }
        C0942Pj0 pj0 = (C0942Pj0) obj;
        CC cc = this.c;
        CC cc2 = pj0.c;
        return (cc == cc2 ? true : cc == null ? false : cc.equals(cc2)) && this.e == pj0.e && this.f == pj0.f && this.d == pj0.d;
    }

    public int hashCode() {
        CC cc = this.c;
        int hashCode = cc == null ? 0 : cc.hashCode();
        long j = this.f;
        return ((((((hashCode + 31) * 31) + this.e) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + this.d;
    }

    public C0942Pj0(int i, int i2, long j) {
        this.c = b;
        this.d = i;
        this.e = i2;
        this.f = j;
    }

    public C0942Pj0(C2740gj0 gj0) {
        C4709sD sDVar = new C4709sD(gj0);
        CC f2 = sDVar.f();
        this.c = f2;
        int i = f2.b;
        if (i >= 0) {
            int i2 = f2.f7794a;
            if (i2 < 24) {
                StringBuilder i3 = AbstractC2531fV.i("Incorrect message size, expecting at least 24, but got: ");
                i3.append(f2.f7794a);
                throw new C4200pE(i3.toString());
            } else if (i == 0 && i2 != 24) {
                StringBuilder i4 = AbstractC2531fV.i("Incorrect message size for a message with 0 fields, expecting 24, but got: ");
                i4.append(f2.f7794a);
                throw new C4200pE(i4.toString());
            } else if (i == 1 && i2 != 32) {
                StringBuilder i5 = AbstractC2531fV.i("Incorrect message size for a message with 1 fields, expecting 32, but got: ");
                i5.append(f2.f7794a);
                throw new C4200pE(i5.toString());
            } else if (sDVar.n(8) == 0) {
                this.d = sDVar.n(12);
                int n = sDVar.n(16);
                this.e = n;
                if (!b(n)) {
                    this.f = 0;
                } else if (f2.f7794a >= 32) {
                    this.f = sDVar.q(24);
                } else {
                    StringBuilder i6 = AbstractC2531fV.i("Incorrect message size, expecting at least 32 for a message with a request identifier, but got: ");
                    i6.append(f2.f7794a);
                    throw new C4200pE(i6.toString());
                }
            } else {
                throw new C4200pE("Non-zero interface ID, expecting zero since associated interfaces are not yet supported.");
            }
        } else {
            StringBuilder i7 = AbstractC2531fV.i("Incorrect number of fields, expecting at least 0, but got: ");
            i7.append(f2.b);
            throw new C4200pE(i7.toString());
        }
    }
}
