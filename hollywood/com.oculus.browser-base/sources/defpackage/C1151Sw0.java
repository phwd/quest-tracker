package defpackage;

/* renamed from: Sw0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1151Sw0 extends AbstractC2360eV {
    public static final C1151Sw0 e;
    public static volatile AbstractC0298Ew0 f;
    public int g;
    public int h;
    public int i;

    static {
        C1151Sw0 sw0 = new C1151Sw0();
        e = sw0;
        AbstractC2360eV.b.put(C1151Sw0.class, sw0);
    }

    @Override // defpackage.AbstractC2360eV
    public final Object e(EnumC2190dV dVVar, Object obj, Object obj2) {
        switch (dVVar.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new UJ0(e, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဍ\u0000\u0002ဍ\u0001", new Object[]{"g", "h", "i"});
            case 3:
                return new C1151Sw0();
            case 4:
                return new C1848bV(e);
            case 5:
                return e;
            case 6:
                AbstractC0298Ew0 ew0 = f;
                if (ew0 == null) {
                    synchronized (C1151Sw0.class) {
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
