package defpackage;

/* renamed from: mP  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3715mP extends AbstractC2360eV {
    public static final C3715mP e;
    public static volatile AbstractC0298Ew0 f;
    public E30 g;
    public E30 h;

    static {
        C3715mP mPVar = new C3715mP();
        e = mPVar;
        AbstractC2360eV.b.put(C3715mP.class, mPVar);
    }

    public C3715mP() {
        C2333eI0 ei0 = C2333eI0.G;
        this.g = ei0;
        this.h = ei0;
    }

    @Override // defpackage.AbstractC2360eV
    public final Object e(EnumC2190dV dVVar, Object obj, Object obj2) {
        switch (dVVar.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new UJ0(e, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0002\u0000\u0001\u001b\u0002\u001b", new Object[]{"g", C3544lP.class, "h", C3202jP.class});
            case 3:
                return new C3715mP();
            case 4:
                return new C1848bV(e);
            case 5:
                return e;
            case 6:
                AbstractC0298Ew0 ew0 = f;
                if (ew0 == null) {
                    synchronized (C3715mP.class) {
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
