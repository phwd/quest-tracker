package defpackage;

/* renamed from: pq1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4300pq1 extends AbstractC2360eV {
    public static final C4300pq1 e;
    public static volatile AbstractC0298Ew0 f;
    public int g;
    public long h;
    public String i = "";
    public int j = -1;
    public int k = -1;
    public boolean l;

    static {
        C4300pq1 pq1 = new C4300pq1();
        e = pq1;
        AbstractC2360eV.b.put(C4300pq1.class, pq1);
    }

    @Override // defpackage.AbstractC2360eV
    public final Object e(EnumC2190dV dVVar, Object obj, Object obj2) {
        switch (dVVar.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new UJ0(e, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဈ\u0001\u0003ဌ\u0002\u0004ဌ\u0003\u0005ဇ\u0004", new Object[]{"g", "h", "i", "j", C4129oq1.f10580a, "k", C3958nq1.f10515a, "l"});
            case 3:
                return new C4300pq1();
            case 4:
                return new C1848bV(e);
            case 5:
                return e;
            case 6:
                AbstractC0298Ew0 ew0 = f;
                if (ew0 == null) {
                    synchronized (C4300pq1.class) {
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
