package defpackage;

/* renamed from: Rv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1088Rv0 extends AbstractC2360eV {
    public static final C1088Rv0 e;
    public static volatile AbstractC0298Ew0 f;
    public int g;
    public String h = "";

    static {
        C1088Rv0 rv0 = new C1088Rv0();
        e = rv0;
        AbstractC2360eV.b.put(C1088Rv0.class, rv0);
    }

    @Override // defpackage.AbstractC2360eV
    public final Object e(EnumC2190dV dVVar, Object obj, Object obj2) {
        switch (dVVar.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new UJ0(e, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဈ\u0000", new Object[]{"g", "h"});
            case 3:
                return new C1088Rv0();
            case 4:
                return new C1848bV(e);
            case 5:
                return e;
            case 6:
                AbstractC0298Ew0 ew0 = f;
                if (ew0 == null) {
                    synchronized (C1088Rv0.class) {
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
