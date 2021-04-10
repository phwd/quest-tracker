package defpackage;

/* renamed from: EB0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class EB0 extends AbstractC2360eV {
    public static final EB0 e;
    public static volatile AbstractC0298Ew0 f;

    static {
        EB0 eb0 = new EB0();
        e = eb0;
        AbstractC2360eV.b.put(EB0.class, eb0);
    }

    public EB0() {
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
                return new UJ0(e, "\u0001\u0000", new Object[0]);
            case 3:
                return new EB0();
            case 4:
                return new C1848bV(e);
            case 5:
                return e;
            case 6:
                AbstractC0298Ew0 ew0 = f;
                if (ew0 == null) {
                    synchronized (EB0.class) {
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
