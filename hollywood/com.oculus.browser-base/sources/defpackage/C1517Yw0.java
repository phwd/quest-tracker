package defpackage;

/* renamed from: Yw0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1517Yw0 extends AbstractC2360eV {
    public static final C1517Yw0 e;
    public static volatile AbstractC0298Ew0 f;
    public int g;
    public String h = "";
    public int i;

    static {
        C1517Yw0 yw0 = new C1517Yw0();
        e = yw0;
        AbstractC2360eV.b.put(C1517Yw0.class, yw0);
    }

    @Override // defpackage.AbstractC2360eV
    public final Object e(EnumC2190dV dVVar, Object obj, Object obj2) {
        switch (dVVar.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new UJ0(e, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002င\u0001", new Object[]{"g", "h", "i"});
            case 3:
                return new C1517Yw0();
            case 4:
                return new C1848bV(e);
            case 5:
                return e;
            case 6:
                AbstractC0298Ew0 ew0 = f;
                if (ew0 == null) {
                    synchronized (C1517Yw0.class) {
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
