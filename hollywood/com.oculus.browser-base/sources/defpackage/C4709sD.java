package defpackage;

import java.nio.ByteOrder;
import java.nio.charset.Charset;

/* renamed from: sD  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4709sD {

    /* renamed from: a  reason: collision with root package name */
    public final C2740gj0 f11257a;
    public final int b;
    public final C4538rD c;

    public C4709sD(C2740gj0 gj0) {
        C4538rD rDVar = new C4538rD((long) gj0.f10015a.limit(), gj0.b.size());
        this.f11257a = gj0;
        gj0.f10015a.order(ByteOrder.LITTLE_ENDIAN);
        this.b = 0;
        this.c = rDVar;
    }

    public void a() {
        this.c.c--;
    }

    public void b() {
        C4538rD rDVar = this.c;
        long j = rDVar.c + 1;
        rDVar.c = j;
        if (j >= 100) {
            throw new C4200pE("Recursion depth limit exceeded.");
        }
    }

    public CC c(CC[] ccArr) {
        CC f = f();
        int length = ccArr.length - 1;
        if (f.b <= ccArr[length].b) {
            CC cc = null;
            while (true) {
                if (length < 0) {
                    break;
                }
                CC cc2 = ccArr[length];
                if (f.b >= cc2.b) {
                    cc = cc2;
                    break;
                }
                length--;
            }
            if (cc == null || cc.f7794a != f.f7794a) {
                throw new C4200pE("Header doesn't correspond to any known version.");
            }
        } else if (f.f7794a < ccArr[length].f7794a) {
            throw new C4200pE("Message newer than the last known version cannot be shorter than required by the last known version.");
        }
        return f;
    }

    public boolean d(int i, int i2) {
        x(i, 1);
        x(i, 1);
        if ((this.f11257a.f10015a.get(this.b + i) & (1 << i2)) != 0) {
            return true;
        }
        return false;
    }

    public byte[] e(int i, int i2, int i3) {
        C4709sD s = s(i, AbstractC5802yh.b(i2));
        if (s == null) {
            return null;
        }
        byte[] bArr = new byte[s.h(1, i3).b];
        s.f11257a.f10015a.position(s.b + 8);
        s.f11257a.f10015a.get(bArr);
        return bArr;
    }

    public CC f() {
        C4538rD rDVar = this.c;
        int i = this.b;
        rDVar.a((long) i, (long) (i + 8));
        CC g = g(0, false);
        C4538rD rDVar2 = this.c;
        int i2 = this.b;
        rDVar2.a((long) (i2 + 8), (long) (i2 + g.f7794a));
        return g;
    }

    public final CC g(int i, boolean z) {
        int n = n(i + 0);
        int n2 = n(i + 4);
        if (n < 0) {
            throw new C4200pE("Negative size. Unsigned integers are not valid for java.");
        } else if (n2 >= 0 || (z && n2 == -1)) {
            return new CC(n, n2);
        } else {
            throw new C4200pE("Negative elements or version. Unsigned integers are not valid for java.");
        }
    }

    public final CC h(long j, int i) {
        CC f = f();
        int i2 = f.b;
        if (((long) f.f7794a) < (j * ((long) i2)) + 8) {
            throw new C4200pE("Array header is incorrect.");
        } else if (i == -1 || i2 == i) {
            return f;
        } else {
            throw new C4200pE("Incorrect array length. Expected: " + i + ", but got: " + f.b + ".");
        }
    }

    public CC i(int i) {
        return h(8, i);
    }

    public CC j(int i) {
        CC g = g(i, true);
        int i2 = g.f7794a;
        if (i2 == 0) {
            if (g.b != 0) {
                StringBuilder i3 = AbstractC2531fV.i("Unexpected version tag for a null union. Expecting 0, found: ");
                i3.append(g.b);
                throw new C4200pE(i3.toString());
            }
        } else if (i2 != 16) {
            throw new C4200pE("Unexpected size of an union. The size must be 0 for a null union, or 16 for a non-null union.");
        }
        return g;
    }

    public double k(int i) {
        x(i, 8);
        return this.f11257a.f10015a.getDouble(this.b + i);
    }

    public float l(int i) {
        x(i, 4);
        return this.f11257a.f10015a.getFloat(this.b + i);
    }

    public float[] m(int i, int i2, int i3) {
        C4709sD s = s(i, AbstractC5802yh.b(i2));
        if (s == null) {
            return null;
        }
        float[] fArr = new float[s.h(4, i3).b];
        s.f11257a.f10015a.position(s.b + 8);
        s.f11257a.f10015a.asFloatBuffer().get(fArr);
        return fArr;
    }

    public int n(int i) {
        x(i, 4);
        return this.f11257a.f10015a.getInt(this.b + i);
    }

    public B30 o(int i, boolean z) {
        AbstractC1552Zj0 H = w(i, z).H();
        if (H == null) {
            return null;
        }
        return new B30(H);
    }

    public int[] p(int i, int i2, int i3) {
        C4709sD s = s(i, AbstractC5802yh.b(i2));
        if (s == null) {
            return null;
        }
        int[] iArr = new int[s.h(4, i3).b];
        s.f11257a.f10015a.position(s.b + 8);
        s.f11257a.f10015a.asIntBuffer().get(iArr);
        return iArr;
    }

    public long q(int i) {
        x(i, 8);
        return this.f11257a.f10015a.getLong(this.b + i);
    }

    public AbstractC1552Zj0 r(int i, boolean z) {
        return w(i, z).H();
    }

    public C4709sD s(int i, boolean z) {
        int i2 = this.b + i;
        long q = q(i);
        if (q != 0) {
            return new C4709sD(this.f11257a, this.c, (int) (((long) i2) + q));
        } else if (z) {
            return null;
        } else {
            throw new C4200pE("Trying to decode null pointer for a non-nullable type.");
        }
    }

    public AbstractC2972i30 t(int i, boolean z, AbstractC2630g30 g30) {
        AbstractC1552Zj0 H = w(i, z).H();
        if (!H.a()) {
            return null;
        }
        return g30.a(H, n(i + 4));
    }

    public short u(int i) {
        x(i, 2);
        return this.f11257a.f10015a.getShort(this.b + i);
    }

    public String v(int i, boolean z) {
        byte[] e = e(i, z ? 1 : 0, -1);
        if (e == null) {
            return null;
        }
        return new String(e, Charset.forName("utf8"));
    }

    public Qp1 w(int i, boolean z) {
        PW pw;
        int n = n(i);
        if (n != -1) {
            C4538rD rDVar = this.c;
            if (n < rDVar.f11191a) {
                throw new C4200pE("Trying to access handle out of order.");
            } else if (((long) n) < rDVar.e) {
                rDVar.f11191a = n + 1;
                pw = (PW) this.f11257a.b.get(n);
            } else {
                throw new C4200pE("Trying to access non present handle.");
            }
        } else if (z) {
            pw = H30.F;
        } else {
            throw new C4200pE("Trying to decode an invalid handle for a non-nullable type.");
        }
        return pw.r();
    }

    public final void x(int i, int i2) {
        if (this.f11257a.f10015a.limit() < i + i2) {
            throw new C4200pE("Buffer is smaller than expected.");
        }
    }

    public C4709sD(C2740gj0 gj0, C4538rD rDVar, int i) {
        this.f11257a = gj0;
        gj0.f10015a.order(ByteOrder.LITTLE_ENDIAN);
        this.b = i;
        this.c = rDVar;
    }
}
