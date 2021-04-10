package defpackage;

/* renamed from: kP  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3373kP extends AbstractC2360eV {
    public static final C3373kP e;
    public static volatile AbstractC0298Ew0 f;
    public int g = 0;
    public Object h;
    public String i = "";

    static {
        C3373kP kPVar = new C3373kP();
        e = kPVar;
        AbstractC2360eV.b.put(C3373kP.class, kPVar);
    }

    @Override // defpackage.AbstractC2360eV
    public final Object e(EnumC2190dV dVVar, Object obj, Object obj2) {
        switch (dVVar.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new UJ0(e, "\u0000\u0004\u0001\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001<\u0000\u0002Ȉ\u0003<\u0000\u0004<\u0000", new Object[]{"h", "g", C3886nP.class, "i", C4057oP.class, C3032iP.class});
            case 3:
                return new C3373kP();
            case 4:
                return new C1848bV(e);
            case 5:
                return e;
            case 6:
                AbstractC0298Ew0 ew0 = f;
                if (ew0 == null) {
                    synchronized (C3373kP.class) {
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
