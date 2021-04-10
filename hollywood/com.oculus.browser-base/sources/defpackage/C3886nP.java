package defpackage;

/* renamed from: nP  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3886nP extends AbstractC2360eV {
    public static final C3886nP e;
    public static volatile AbstractC0298Ew0 f;
    public AbstractC1248Uk g = AbstractC1248Uk.F;

    static {
        C3886nP nPVar = new C3886nP();
        e = nPVar;
        AbstractC2360eV.b.put(C3886nP.class, nPVar);
    }

    @Override // defpackage.AbstractC2360eV
    public final Object e(EnumC2190dV dVVar, Object obj, Object obj2) {
        switch (dVVar.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new UJ0(e, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\n", new Object[]{"g"});
            case 3:
                return new C3886nP();
            case 4:
                return new C1848bV(e);
            case 5:
                return e;
            case 6:
                AbstractC0298Ew0 ew0 = f;
                if (ew0 == null) {
                    synchronized (C3886nP.class) {
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
