package defpackage;

/* renamed from: Sv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1149Sv0 extends AbstractC2360eV {
    public static final C1149Sv0 e;
    public static volatile AbstractC0298Ew0 f;
    public int g;
    public C1088Rv0 h;

    static {
        C1149Sv0 sv0 = new C1149Sv0();
        e = sv0;
        AbstractC2360eV.b.put(C1149Sv0.class, sv0);
    }

    public C1149Sv0() {
        C2333eI0 ei0 = C2333eI0.G;
    }

    @Override // defpackage.AbstractC2360eV
    public final Object e(EnumC2190dV dVVar, Object obj, Object obj2) {
        switch (dVVar.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new UJ0(e, "\u0001\u0001\u0000\u0001\u0003\u0003\u0001\u0000\u0000\u0000\u0003ဉ\u0001", new Object[]{"g", "h"});
            case 3:
                return new C1149Sv0();
            case 4:
                return new C1848bV(e);
            case 5:
                return e;
            case 6:
                AbstractC0298Ew0 ew0 = f;
                if (ew0 == null) {
                    synchronized (C1149Sv0.class) {
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
