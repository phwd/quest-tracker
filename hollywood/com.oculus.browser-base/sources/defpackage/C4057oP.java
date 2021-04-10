package defpackage;

/* renamed from: oP  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4057oP extends AbstractC2360eV {
    public static final C4057oP e;
    public static volatile AbstractC0298Ew0 f;
    public int g;

    static {
        C4057oP oPVar = new C4057oP();
        e = oPVar;
        AbstractC2360eV.b.put(C4057oP.class, oPVar);
    }

    @Override // defpackage.AbstractC2360eV
    public final Object e(EnumC2190dV dVVar, Object obj, Object obj2) {
        switch (dVVar.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new UJ0(e, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f", new Object[]{"g"});
            case 3:
                return new C4057oP();
            case 4:
                return new C1848bV(e);
            case 5:
                return e;
            case 6:
                AbstractC0298Ew0 ew0 = f;
                if (ew0 == null) {
                    synchronized (C4057oP.class) {
                        ew0 = f;
                        if (ew0 == null) {
                            ew0 = new C2019cV(e);
                        }
                    }
                }
                return ew0;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public int m() {
        int i = this.g;
        int i2 = 2;
        if (i == 0) {
            i2 = 1;
        } else if (i != 1) {
            i2 = i != 2 ? 0 : 3;
        }
        if (i2 == 0) {
            return 4;
        }
        return i2;
    }
}
