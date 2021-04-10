package defpackage;

/* renamed from: fY  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2537fY extends AbstractC4340q31 {
    public static final CC[] b;
    public static final CC c;
    public C2366eY[] d;

    static {
        CC[] ccArr = {new CC(16, 0)};
        b = ccArr;
        c = ccArr[0];
    }

    public C2537fY(int i) {
        super(16, i);
    }

    public static C2537fY d(C4709sD sDVar) {
        C2366eY eYVar;
        if (sDVar == null) {
            return null;
        }
        sDVar.b();
        try {
            C2537fY fYVar = new C2537fY(sDVar.c(b).b);
            C4709sD s = sDVar.s(8, false);
            CC i = s.i(-1);
            fYVar.d = new C2366eY[i.b];
            for (int i2 = 0; i2 < i.b; i2++) {
                sDVar = AbstractC2531fV.n(i2, 8, 8, s, false);
                C2366eY[] eYVarArr = fYVar.d;
                CC[] ccArr = C2366eY.b;
                if (sDVar == null) {
                    eYVar = null;
                } else {
                    sDVar.b();
                    try {
                        eYVar = new C2366eY(sDVar.c(C2366eY.b).b);
                        eYVar.d = sDVar.v(8, false);
                        eYVar.e = sDVar.v(16, false);
                    } finally {
                        sDVar.a();
                    }
                }
                eYVarArr[i2] = eYVar;
            }
            sDVar.a();
            return fYVar;
        } finally {
            sDVar.a();
        }
    }

    @Override // defpackage.AbstractC4340q31
    public final void a(C1648aL aLVar) {
        C1648aL x = aLVar.x(c);
        C2366eY[] eYVarArr = this.d;
        if (eYVarArr == null) {
            x.s(8, false);
            return;
        }
        C1648aL t = x.t(eYVarArr.length, 8, -1);
        int i = 0;
        while (true) {
            C2366eY[] eYVarArr2 = this.d;
            if (i < eYVarArr2.length) {
                t.i(eYVarArr2[i], (i * 8) + 8, false);
                i++;
            } else {
                return;
            }
        }
    }
}
