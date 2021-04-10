package X;

import java.lang.reflect.Member;
import java.util.HashMap;

/* renamed from: X.Xr  reason: case insensitive filesystem */
public final class C0244Xr {
    public CC A00;
    public CB A01;
    public CB A02;
    public CB A03;
    public CB A04;
    public CB A05;
    public CB A06;
    public CB A07;
    public CB A08;
    public AnonymousClass8I[] A09;
    public AnonymousClass8I[] A0A = null;
    public final jm A0B;
    public final boolean A0C;

    public static final void A00(C0244Xr xr, CB cb, CB cb2, String str) {
        if (cb2 != null && cb2.getClass() == cb.getClass()) {
            StringBuilder sb = new StringBuilder("Conflicting ");
            sb.append(str);
            sb.append(" creators: already had ");
            sb.append(cb2);
            sb.append(", encountered ");
            sb.append(cb);
            throw new IllegalArgumentException(sb.toString());
        } else if (xr.A0C) {
            Mv.A05((Member) cb.A0M());
        }
    }

    public final void A01(CB cb, AnonymousClass8I[] r4) {
        A00(this, cb, this.A03, "delegate");
        this.A03 = cb;
        this.A09 = r4;
    }

    public final void A02(CB cb, AnonymousClass8I[] r8) {
        Object put;
        A00(this, cb, this.A07, "property-based");
        this.A07 = cb;
        int length = r8.length;
        if (length > 1) {
            HashMap hashMap = new HashMap();
            for (int i = 0; i < length; i++) {
                String str = r8[i]._propName;
                if ((str.length() != 0 || r8[i].A04() == null) && (put = hashMap.put(str, Integer.valueOf(i))) != null) {
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
        this.A0A = r8;
    }

    public C0244Xr(jm jmVar, boolean z) {
        this.A0B = jmVar;
        this.A0C = z;
    }
}
