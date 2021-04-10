package X;

import java.io.Serializable;
import java.util.Map;

/* renamed from: X.2I  reason: invalid class name */
public final class AnonymousClass2I extends V4 implements Serializable {
    public static final long serialVersionUID = -4227480407273773599L;
    public final int _deserFeatures = AbstractC1032r3.A00(EnumC1023qs.class);
    public final PW _nodeFactory = PW.A00;
    public final QB _problemHandlers = null;

    @Override // X.AbstractC1032r3
    public final AbstractC1020qp A01() {
        if (A05(EnumC1027qy.USE_ANNOTATIONS)) {
            return super.A01();
        }
        return AnonymousClass0n.A00;
    }

    public AnonymousClass2I(OS os, PP pp, Map map) {
        super(os, pp, map);
    }

    @Override // X.AbstractC1032r3
    public final PN A04() {
        PN A04 = super.A04();
        if (!A05(EnumC1027qy.AUTO_DETECT_SETTERS)) {
            A04 = A04.A5g(EnumC0242Mp.NONE);
        }
        if (!A05(EnumC1027qy.AUTO_DETECT_CREATORS)) {
            A04 = A04.A5b(EnumC0242Mp.NONE);
        }
        if (!A05(EnumC1027qy.AUTO_DETECT_FIELDS)) {
            return A04.A5c(EnumC0242Mp.NONE);
        }
        return A04;
    }
}
