package X;

import java.io.Serializable;
import java.util.Collections;

/* renamed from: X.rM  reason: case insensitive filesystem */
public final class C1047rM extends PG implements Serializable {
    public static final C1047rM A00 = new C1047rM();
    public static final C1046rL A01;
    public static final C1046rL A02;
    public static final C1046rL A03;
    public static final C1046rL A04 = new C1046rL(null, fF.A00(String.class), C1043rI.A01(String.class, null, null), Collections.emptyList());
    public static final long serialVersionUID = 1;

    static {
        Class cls = Boolean.TYPE;
        A01 = new C1046rL(null, fF.A00(cls), C1043rI.A01(cls, null, null), Collections.emptyList());
        Class cls2 = Integer.TYPE;
        A02 = new C1046rL(null, fF.A00(cls2), C1043rI.A01(cls2, null, null), Collections.emptyList());
        Class cls3 = Long.TYPE;
        A03 = new C1046rL(null, fF.A00(cls3), C1043rI.A01(cls3, null, null), Collections.emptyList());
    }

    public static final C1046rL A00(AbstractC1024qt qtVar) {
        Class cls = qtVar._class;
        if (cls == String.class) {
            return A04;
        }
        if (cls == Boolean.TYPE) {
            return A01;
        }
        if (cls == Integer.TYPE) {
            return A02;
        }
        if (cls == Long.TYPE) {
            return A03;
        }
        return null;
    }

    public static final PK A01(C1047rM rMVar, AbstractC1032r3 r3Var, AbstractC1024qt qtVar, PF pf, boolean z) {
        AbstractC1020qp qpVar;
        boolean A05 = r3Var.A05(EnumC1027qy.USE_ANNOTATIONS);
        Class cls = qtVar._class;
        if (A05) {
            qpVar = r3Var.A01();
        } else {
            qpVar = null;
        }
        PK pk = new PK(r3Var, z, qtVar, C1043rI.A00(cls, qpVar, pf), "set");
        pk.A07();
        return pk;
    }
}
