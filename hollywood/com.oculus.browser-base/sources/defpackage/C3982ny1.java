package defpackage;

/* renamed from: ny1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3982ny1 extends AbstractC2360eV {
    public static final C3982ny1 e;
    public static volatile AbstractC0298Ew0 f;
    public int g;
    public long h;
    public int i;

    static {
        C3982ny1 ny1 = new C3982ny1();
        e = ny1;
        AbstractC2360eV.b.put(C3982ny1.class, ny1);
    }

    @Override // defpackage.AbstractC2360eV
    public final Object e(EnumC2190dV dVVar, Object obj, Object obj2) {
        switch (dVVar.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new UJ0(e, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဂ\u0000\u0002င\u0001", new Object[]{"g", "h", "i"});
            case 3:
                return new C3982ny1();
            case 4:
                return new C1848bV(e);
            case 5:
                return e;
            case 6:
                AbstractC0298Ew0 ew0 = f;
                if (ew0 == null) {
                    synchronized (C3982ny1.class) {
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
