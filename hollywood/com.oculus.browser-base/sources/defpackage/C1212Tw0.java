package defpackage;

/* renamed from: Tw0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1212Tw0 extends AbstractC2360eV {
    public static final C1212Tw0 e;
    public static volatile AbstractC0298Ew0 f;
    public int g;
    public int h;
    public int i;
    public long j;
    public C1151Sw0 k;
    public float l;
    public E30 m = C2333eI0.G;

    static {
        C1212Tw0 tw0 = new C1212Tw0();
        e = tw0;
        AbstractC2360eV.b.put(C1212Tw0.class, tw0);
    }

    @Override // defpackage.AbstractC2360eV
    public final Object e(EnumC2190dV dVVar, Object obj, Object obj2) {
        switch (dVVar.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new UJ0(e, "\u0001\u0006\u0000\u0001\u0001\u0017\u0006\u0000\u0001\u0000\u0001ဌ\u0000\u0002ဌ\u0001\u0003ဂ\u0002\u0005ဉ\u0003\u0007ခ\u0004\u0017\u001b", new Object[]{"g", "h", C1334Vw0.f9117a, "i", C1273Uw0.f9055a, "j", "k", "l", "m", C1578Zw0.class});
            case 3:
                return new C1212Tw0();
            case 4:
                return new C1848bV(e);
            case 5:
                return e;
            case 6:
                AbstractC0298Ew0 ew0 = f;
                if (ew0 == null) {
                    synchronized (C1212Tw0.class) {
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
