package defpackage;

/* renamed from: iP  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3032iP extends AbstractC2360eV {
    public static final C3032iP e;
    public static volatile AbstractC0298Ew0 f;

    static {
        C3032iP iPVar = new C3032iP();
        e = iPVar;
        AbstractC2360eV.b.put(C3032iP.class, iPVar);
    }

    @Override // defpackage.AbstractC2360eV
    public final Object e(EnumC2190dV dVVar, Object obj, Object obj2) {
        switch (dVVar.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new UJ0(e, "\u0000\u0000", new Object[0]);
            case 3:
                return new C3032iP();
            case 4:
                return new C1848bV(e);
            case 5:
                return e;
            case 6:
                AbstractC0298Ew0 ew0 = f;
                if (ew0 == null) {
                    synchronized (C3032iP.class) {
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
