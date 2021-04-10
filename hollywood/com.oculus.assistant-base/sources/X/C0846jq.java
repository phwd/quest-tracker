package X;

import java.util.ArrayList;

/* renamed from: X.jq  reason: case insensitive filesystem */
public final class C0846jq extends DQ {
    public final ArrayList A00;

    public static final void A00(C0846jq jqVar, DQ dq) {
        if (jqVar.A03) {
            dq.A02();
            A01(jqVar, dq);
            dq.A02();
            dq.A00 = jqVar;
            return;
        }
        throw new IllegalStateException("Expected object to be mutable");
    }

    public static void A01(C0846jq jqVar, Object obj) {
        if (jqVar.A03) {
            jqVar.A00.add(obj);
            return;
        }
        throw new IllegalStateException("Expected object to be mutable");
    }

    public C0846jq(int i) {
        this.A00 = new ArrayList(i);
    }
}
