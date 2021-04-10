package defpackage;

import java.util.Objects;

/* renamed from: TO0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class TO0 extends AbstractC2360eV {
    public static final TO0 e;
    public static volatile AbstractC0298Ew0 f;
    public int g;
    public long h;
    public E30 i = C2333eI0.G;
    public int j;
    public boolean k;

    static {
        TO0 to0 = new TO0();
        e = to0;
        AbstractC2360eV.b.put(TO0.class, to0);
    }

    public static OO0 n() {
        Objects.requireNonNull(e);
        return new OO0(null);
    }

    @Override // defpackage.AbstractC2360eV
    public final Object e(EnumC2190dV dVVar, Object obj, Object obj2) {
        switch (dVVar.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new UJ0(e, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0001\u0000\u0001\f\u0002\u0002\u0003\u001b\u0004\f\u0005\u0007", new Object[]{"g", "h", "i", SO0.class, "j", "k"});
            case 3:
                return new TO0();
            case 4:
                return new OO0(null);
            case 5:
                return e;
            case 6:
                AbstractC0298Ew0 ew0 = f;
                if (ew0 == null) {
                    synchronized (TO0.class) {
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

    public int m() {
        int i2 = this.g;
        int i3 = 2;
        if (i2 == 0) {
            i3 = 1;
        } else if (i2 != 1) {
            i3 = i2 != 2 ? 0 : 3;
        }
        if (i3 == 0) {
            return 4;
        }
        return i3;
    }
}
