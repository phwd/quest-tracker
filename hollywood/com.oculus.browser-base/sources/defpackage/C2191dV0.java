package defpackage;

/* renamed from: dV0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2191dV0 extends AbstractC2360eV {
    public static final C2191dV0 e;
    public static volatile AbstractC0298Ew0 f;
    public int g;
    public long h;
    public long i;
    public String j = "";
    public long k;
    public long l;

    static {
        C2191dV0 dv0 = new C2191dV0();
        e = dv0;
        AbstractC2360eV.b.put(C2191dV0.class, dv0);
    }

    @Override // defpackage.AbstractC2360eV
    public final Object e(EnumC2190dV dVVar, Object obj, Object obj2) {
        switch (dVVar.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new UJ0(e, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဂ\u0001\u0003ဈ\u0002\u0004ဂ\u0003\u0005ဂ\u0004", new Object[]{"g", "h", "i", "j", "k", "l"});
            case 3:
                return new C2191dV0();
            case 4:
                return new C1848bV(e);
            case 5:
                return e;
            case 6:
                AbstractC0298Ew0 ew0 = f;
                if (ew0 == null) {
                    synchronized (C2191dV0.class) {
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
