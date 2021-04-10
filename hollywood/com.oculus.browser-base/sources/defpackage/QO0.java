package defpackage;

/* renamed from: QO0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class QO0 extends AbstractC2360eV {
    public static final QO0 e;
    public static volatile AbstractC0298Ew0 f;
    public int g = 0;
    public Object h;

    static {
        QO0 qo0 = new QO0();
        e = qo0;
        AbstractC2360eV.b.put(QO0.class, qo0);
    }

    public static PO0 m() {
        return new PO0(null);
    }

    @Override // defpackage.AbstractC2360eV
    public final Object e(EnumC2190dV dVVar, Object obj, Object obj2) {
        switch (dVVar.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new UJ0(e, "\u0000\u0005\u0001\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001:\u0000\u00023\u0000\u00037\u0000\u00045\u0000\u0005Ȼ\u0000", new Object[]{"h", "g"});
            case 3:
                return new QO0();
            case 4:
                return new PO0(null);
            case 5:
                return e;
            case 6:
                AbstractC0298Ew0 ew0 = f;
                if (ew0 == null) {
                    synchronized (QO0.class) {
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
