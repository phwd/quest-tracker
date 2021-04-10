package defpackage;

/* renamed from: SO0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class SO0 extends AbstractC2360eV {
    public static final SO0 e;
    public static volatile AbstractC0298Ew0 f;
    public String g = "";
    public int h;
    public E30 i = C2333eI0.G;

    static {
        SO0 so0 = new SO0();
        e = so0;
        AbstractC2360eV.b.put(SO0.class, so0);
    }

    @Override // defpackage.AbstractC2360eV
    public final Object e(EnumC2190dV dVVar, Object obj, Object obj2) {
        switch (dVVar.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new UJ0(e, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0001\u0000\u0001Ȉ\u0002\f\u0003\u001b", new Object[]{"g", "h", "i", QO0.class});
            case 3:
                return new SO0();
            case 4:
                return new C1848bV(e);
            case 5:
                return e;
            case 6:
                AbstractC0298Ew0 ew0 = f;
                if (ew0 == null) {
                    synchronized (SO0.class) {
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

    public RO0 m() {
        RO0 ro0;
        switch (this.h) {
            case 0:
                ro0 = RO0.SINGLE;
                break;
            case 1:
                ro0 = RO0.BOOLEAN_ARRAY;
                break;
            case 2:
                ro0 = RO0.DOUBLE_ARRAY;
                break;
            case 3:
                ro0 = RO0.INT_ARRAY;
                break;
            case 4:
                ro0 = RO0.LONG_ARRAY;
                break;
            case 5:
                ro0 = RO0.STRING_ARRAY;
                break;
            case 6:
                ro0 = RO0.NULL;
                break;
            default:
                ro0 = null;
                break;
        }
        return ro0 == null ? RO0.UNRECOGNIZED : ro0;
    }
}
