package X;

import java.lang.reflect.Member;
import java.util.HashMap;

/* renamed from: X.0nG  reason: invalid class name */
public final class AnonymousClass0nG {
    public AnonymousClass0KB A00;
    public AnonymousClass0KA A01;
    public AnonymousClass0KA A02;
    public AnonymousClass0KA A03;
    public AnonymousClass0KA A04;
    public AnonymousClass0KA A05;
    public AnonymousClass0KA A06;
    public AnonymousClass0KA A07;
    public AnonymousClass0KA A08;
    public AnonymousClass0F3[] A09;
    public AnonymousClass0F3[] A0A = null;
    public final AbstractC06260mR A0B;
    public final boolean A0C;

    public static final void A00(AnonymousClass0nG r2, AnonymousClass0KA r3, AnonymousClass0KA r4, String str) {
        if (r4 != null && r4.getClass() == r3.getClass()) {
            throw new IllegalArgumentException("Conflicting " + str + " creators: already had " + r4 + ", encountered " + r3);
        } else if (r2.A0C) {
            C07130om.A06((Member) r3.A0N());
        }
    }

    public final void A01(AnonymousClass0KA r3, AnonymousClass0F3[] r4) {
        A00(this, r3, this.A03, "delegate");
        this.A03 = r3;
        this.A09 = r4;
    }

    public final void A02(AnonymousClass0KA r7, AnonymousClass0F3[] r8) {
        Object put;
        A00(this, r7, this.A07, "property-based");
        this.A07 = r7;
        int length = r8.length;
        if (length > 1) {
            HashMap hashMap = new HashMap();
            for (int i = 0; i < length; i++) {
                String str = r8[i]._propName;
                if ((str.length() != 0 || r8[i].A04() == null) && (put = hashMap.put(str, Integer.valueOf(i))) != null) {
                    throw new IllegalArgumentException("Duplicate creator property \"" + str + "\" (index " + put + " vs " + i + ")");
                }
            }
        }
        this.A0A = r8;
    }

    public AnonymousClass0nG(AbstractC06260mR r2, boolean z) {
        this.A0B = r2;
        this.A0C = z;
    }
}
