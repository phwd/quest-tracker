package X;

import java.lang.reflect.Member;
import java.util.HashMap;

public final class Op {
    public Ss A00;
    public SV A01;
    public SV A02;
    public SV A03;
    public SV A04;
    public SV A05;
    public SV A06;
    public SV A07;
    public SV A08;
    public C0372Ui[] A09;
    public C0372Ui[] A0A = null;
    public final O4 A0B;
    public final boolean A0C;

    public static final void A00(Op op, SV sv, SV sv2, String str) {
        if (sv2 != null && sv2.getClass() == sv.getClass()) {
            StringBuilder sb = new StringBuilder("Conflicting ");
            sb.append(str);
            sb.append(" creators: already had ");
            sb.append(sv2);
            sb.append(", encountered ");
            sb.append(sv);
            throw new IllegalArgumentException(sb.toString());
        } else if (op.A0C) {
            Q5.A06((Member) sv.A0M());
        }
    }

    public final void A01(SV sv, C0372Ui[] uiArr) {
        A00(this, sv, this.A03, "delegate");
        this.A03 = sv;
        this.A09 = uiArr;
    }

    public final void A02(SV sv, C0372Ui[] uiArr) {
        Object put;
        C0372Ui ui;
        A00(this, sv, this.A07, "property-based");
        this.A07 = sv;
        int length = uiArr.length;
        if (length > 1) {
            HashMap hashMap = new HashMap();
            for (int i = 0; i < length; i++) {
                String str = uiArr[i]._propName;
                if ((str.length() != 0 || (ui = uiArr[i]) == null || ui._injectableValueId == null) && (put = hashMap.put(str, Integer.valueOf(i))) != null) {
                    StringBuilder sb = new StringBuilder("Duplicate creator property \"");
                    sb.append(str);
                    sb.append("\" (index ");
                    sb.append(put);
                    sb.append(" vs ");
                    sb.append(i);
                    sb.append(")");
                    throw new IllegalArgumentException(sb.toString());
                }
            }
        }
        this.A0A = uiArr;
    }

    public Op(O4 o4, boolean z) {
        this.A0B = o4;
        this.A0C = z;
    }
}
