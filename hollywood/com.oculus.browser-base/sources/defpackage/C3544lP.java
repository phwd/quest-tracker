package defpackage;

/* renamed from: lP  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3544lP extends AbstractC2360eV {
    public static final C3544lP e;
    public static volatile AbstractC0298Ew0 f;
    public int g = 0;
    public Object h;

    static {
        C3544lP lPVar = new C3544lP();
        e = lPVar;
        AbstractC2360eV.b.put(C3544lP.class, lPVar);
    }

    @Override // defpackage.AbstractC2360eV
    public final Object e(EnumC2190dV dVVar, Object obj, Object obj2) {
        switch (dVVar.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new UJ0(e, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001<\u0000\u0002Ȼ\u0000", new Object[]{"h", "g", C3373kP.class});
            case 3:
                return new C3544lP();
            case 4:
                return new C1848bV(e);
            case 5:
                return e;
            case 6:
                AbstractC0298Ew0 ew0 = f;
                if (ew0 == null) {
                    synchronized (C3544lP.class) {
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
