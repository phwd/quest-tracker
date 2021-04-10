package defpackage;

/* renamed from: ps1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4306ps1 extends AbstractC5658xp1 {
    public boolean[] b;
    public long[] c;
    public String[] d;
    public C4901tL[] e;

    public static final C4306ps1 b(C4709sD sDVar, int i) {
        CC j = sDVar.j(i);
        boolean[] zArr = null;
        long[] jArr = null;
        if (j.f7794a == 0) {
            return null;
        }
        C4306ps1 ps1 = new C4306ps1();
        int i2 = j.b;
        if (i2 == 0) {
            C4709sD s = sDVar.s(i + 8, AbstractC5802yh.b(0));
            if (s != null) {
                CC f = s.f();
                int i3 = f.f7794a;
                int i4 = (f.b + 7) / 8;
                if (i3 >= i4 + 8) {
                    byte[] bArr = new byte[i4];
                    s.f11257a.f10015a.position(s.b + 8);
                    s.f11257a.f10015a.get(bArr);
                    int i5 = f.b;
                    boolean[] zArr2 = new boolean[i5];
                    for (int i6 = 0; i6 < i4; i6++) {
                        for (int i7 = 0; i7 < 8; i7++) {
                            int i8 = (i6 * 8) + i7;
                            if (i8 < i5) {
                                zArr2[i8] = (bArr[i6] & (1 << i7)) != 0;
                            }
                        }
                    }
                    zArr = zArr2;
                } else {
                    throw new C4200pE("Array header is incorrect.");
                }
            }
            ps1.b = zArr;
            ps1.f11638a = 0;
        } else if (i2 == 1) {
            C4709sD s2 = sDVar.s(i + 8, AbstractC5802yh.b(0));
            if (s2 != null) {
                jArr = new long[s2.h(8, -1).b];
                s2.f11257a.f10015a.position(s2.b + 8);
                s2.f11257a.f10015a.asLongBuffer().get(jArr);
            }
            ps1.c = jArr;
            ps1.f11638a = 1;
        } else if (i2 == 2) {
            C4709sD s3 = sDVar.s(i + 8, false);
            CC i9 = s3.i(-1);
            ps1.d = new String[i9.b];
            for (int i10 = 0; i10 < i9.b; i10++) {
                ps1.d[i10] = s3.v((i10 * 8) + 8, false);
            }
            ps1.f11638a = 2;
        } else if (i2 == 3) {
            C4709sD s4 = sDVar.s(i + 8, false);
            CC i11 = s4.i(-1);
            ps1.e = new C4901tL[i11.b];
            for (int i12 = 0; i12 < i11.b; i12++) {
                ps1.e[i12] = C4901tL.d(AbstractC2531fV.n(i12, 8, 8, s4, false));
            }
            ps1.f11638a = 3;
        }
        return ps1;
    }

    @Override // defpackage.AbstractC5658xp1
    public final void a(C1648aL aLVar, int i) {
        aLVar.c(16, i);
        aLVar.c(this.f11638a, i + 4);
        int i2 = this.f11638a;
        boolean b2 = AbstractC5802yh.b(0);
        if (i2 == 0) {
            boolean[] zArr = this.b;
            int i3 = i + 8;
            if (zArr == null) {
                aLVar.s(i3, b2);
                return;
            }
            int length = (zArr.length + 7) / 8;
            byte[] bArr = new byte[length];
            for (int i4 = 0; i4 < length; i4++) {
                for (int i5 = 0; i5 < 8; i5++) {
                    int i6 = (i4 * 8) + i5;
                    if (i6 < zArr.length && zArr[i6]) {
                        bArr[i4] = (byte) (bArr[i4] | ((byte) (1 << i5)));
                    }
                }
            }
            aLVar.q(bArr, zArr.length, i3);
        } else if (i2 == 1) {
            long[] jArr = this.c;
            int i7 = i + 8;
            if (jArr == null) {
                aLVar.s(i7, b2);
                return;
            }
            C1648aL v = aLVar.v(8, jArr.length, i7, -1);
            v.b.b.position(v.f9425a + 8);
            v.b.b.asLongBuffer().put(jArr);
        } else if (i2 == 2) {
            String[] strArr = this.d;
            if (strArr == null) {
                aLVar.s(i + 8, false);
                return;
            }
            C1648aL t = aLVar.t(strArr.length, i + 8, -1);
            int i8 = 0;
            while (true) {
                String[] strArr2 = this.d;
                if (i8 < strArr2.length) {
                    t.k(strArr2[i8], (i8 * 8) + 8, false);
                    i8++;
                } else {
                    return;
                }
            }
        } else if (i2 == 3) {
            C4901tL[] tLVarArr = this.e;
            if (tLVarArr == null) {
                aLVar.s(i + 8, false);
                return;
            }
            C1648aL t2 = aLVar.t(tLVarArr.length, i + 8, -1);
            int i9 = 0;
            while (true) {
                C4901tL[] tLVarArr2 = this.e;
                if (i9 < tLVarArr2.length) {
                    t2.i(tLVarArr2[i9], (i9 * 8) + 8, false);
                    i9++;
                } else {
                    return;
                }
            }
        }
    }
}
