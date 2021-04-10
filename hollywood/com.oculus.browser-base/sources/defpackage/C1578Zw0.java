package defpackage;

/* renamed from: Zw0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1578Zw0 extends AbstractC2360eV {
    public static final C1578Zw0 e;
    public static volatile AbstractC0298Ew0 f;
    public int g;
    public int h = 0;
    public Object i;
    public boolean j;
    public long k;

    static {
        C1578Zw0 zw0 = new C1578Zw0();
        e = zw0;
        AbstractC2360eV.b.put(C1578Zw0.class, zw0);
    }

    @Override // defpackage.AbstractC2360eV
    public final Object e(EnumC2190dV dVVar, Object obj, Object obj2) {
        switch (dVVar.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new UJ0(e, "\u0001\u0004\u0001\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ြ\u0000\u0002ြ\u0000\u0003ဇ\u0002\u0004ဂ\u0003", new Object[]{"i", "h", "g", C1517Yw0.class, C1456Xw0.class, "j", "k"});
            case 3:
                return new C1578Zw0();
            case 4:
                return new C1848bV(e);
            case 5:
                return e;
            case 6:
                AbstractC0298Ew0 ew0 = f;
                if (ew0 == null) {
                    synchronized (C1578Zw0.class) {
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
}
