package defpackage;

/* renamed from: vB  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5213vB extends AbstractC2360eV {
    public static final C5213vB e;
    public static volatile AbstractC0298Ew0 f;
    public int g;
    public int h;
    public int i;
    public long j;
    public AbstractC1248Uk k = AbstractC1248Uk.F;
    public int l;
    public String m = "";
    public int n;
    public int o;

    static {
        C5213vB vBVar = new C5213vB();
        e = vBVar;
        AbstractC2360eV.b.put(C5213vB.class, vBVar);
    }

    @Override // defpackage.AbstractC2360eV
    public final Object e(EnumC2190dV dVVar, Object obj, Object obj2) {
        switch (dVVar.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new UJ0(e, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003ဂ\u0002\u0004ည\u0003\u0005င\u0004\u0006ဈ\u0005\u0007င\u0006\bဌ\u0007", new Object[]{"g", "h", "i", "j", "k", "l", "m", "n", "o", C5043uB.f11395a});
            case 3:
                return new C5213vB();
            case 4:
                return new C1848bV(e);
            case 5:
                return e;
            case 6:
                AbstractC0298Ew0 ew0 = f;
                if (ew0 == null) {
                    synchronized (C5213vB.class) {
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
