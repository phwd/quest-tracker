package defpackage;

/* renamed from: py1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4324py1 extends AbstractC2360eV {
    public static final C4324py1 e;
    public static volatile AbstractC0298Ew0 f;
    public int g;
    public String h = "";
    public C3982ny1 i;
    public int j;

    static {
        C4324py1 py1 = new C4324py1();
        e = py1;
        AbstractC2360eV.b.put(C4324py1.class, py1);
    }

    @Override // defpackage.AbstractC2360eV
    public final Object e(EnumC2190dV dVVar, Object obj, Object obj2) {
        switch (dVVar.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new UJ0(e, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဉ\u0001\u0003ဌ\u0002", new Object[]{"g", "h", "i", "j", C4153oy1.f11040a});
            case 3:
                return new C4324py1();
            case 4:
                return new C1848bV(e);
            case 5:
                return e;
            case 6:
                AbstractC0298Ew0 ew0 = f;
                if (ew0 == null) {
                    synchronized (C4324py1.class) {
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
