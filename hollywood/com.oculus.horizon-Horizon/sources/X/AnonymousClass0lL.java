package X;

import java.lang.reflect.Member;
import java.util.HashMap;

/* renamed from: X.0lL  reason: invalid class name */
public final class AnonymousClass0lL {
    public AnonymousClass0GW A00;
    public AnonymousClass0GV A01;
    public AnonymousClass0GV A02;
    public AnonymousClass0GV A03;
    public AnonymousClass0GV A04;
    public AnonymousClass0GV A05;
    public AnonymousClass0GV A06;
    public AnonymousClass0GV A07;
    public AnonymousClass0GV A08;
    public AnonymousClass08T[] A09;
    public AnonymousClass08T[] A0A = null;
    public final AbstractC05180kU A0B;
    public final boolean A0C;

    public static final void A00(AnonymousClass0lL r2, AnonymousClass0GV r3, AnonymousClass0GV r4, String str) {
        if (r4 != null && r4.getClass() == r3.getClass()) {
            StringBuilder sb = new StringBuilder("Conflicting ");
            sb.append(str);
            sb.append(" creators: already had ");
            sb.append(r4);
            sb.append(", encountered ");
            sb.append(r3);
            throw new IllegalArgumentException(sb.toString());
        } else if (r2.A0C) {
            C06330mu.A05((Member) r3.A0M());
        }
    }

    public final void A01(AnonymousClass0GV r3, AnonymousClass08T[] r4) {
        A00(this, r3, this.A03, "delegate");
        this.A03 = r3;
        this.A09 = r4;
    }

    public final void A02(AnonymousClass0GV r7, AnonymousClass08T[] r8) {
        Object put;
        A00(this, r7, this.A07, "property-based");
        this.A07 = r7;
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

    public AnonymousClass0lL(AbstractC05180kU r2, boolean z) {
        this.A0B = r2;
        this.A0C = z;
    }
}
