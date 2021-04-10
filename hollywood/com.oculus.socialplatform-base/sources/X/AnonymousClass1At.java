package X;

import androidx.annotation.VisibleForTesting;

/* renamed from: X.1At  reason: invalid class name */
public final class AnonymousClass1At {
    @VisibleForTesting
    public final AnonymousClass02n<AnonymousClass1Ah> A00 = new AnonymousClass02n<>();
    @VisibleForTesting
    public final C000502v<AnonymousClass1Ah, AnonymousClass1B9> A01 = new C000502v<>();

    public static AnonymousClass1BS A00(AnonymousClass1At r6, AnonymousClass1Ah r7, int i) {
        AnonymousClass1B9 r3;
        AnonymousClass1BS r1;
        C000502v<AnonymousClass1Ah, AnonymousClass1B9> r62 = r6.A01;
        int A05 = r62.A05(r7);
        if (A05 >= 0 && (r3 = (AnonymousClass1B9) r62.A02[(A05 << 1) + 1]) != null) {
            int i2 = r3.A00;
            if ((i2 & i) != 0) {
                int i3 = i2 & (i ^ -1);
                r3.A00 = i3;
                if (i == 4) {
                    r1 = r3.A02;
                } else if (i == 8) {
                    r1 = r3.A01;
                } else {
                    throw new IllegalArgumentException("Must provide flag PRE or POST");
                }
                if ((i3 & 12) == 0) {
                    r62.A06(A05);
                    r3.A00 = 0;
                    r3.A02 = null;
                    r3.A01 = null;
                    AnonymousClass1B9.A03.A8z(r3);
                }
                return r1;
            }
        }
        return null;
    }

    public final void A01(AnonymousClass1Ah r3) {
        AnonymousClass1B9 r1 = this.A01.get(r3);
        if (r1 != null) {
            r1.A00 &= -2;
        }
    }

    public final void A02(AnonymousClass1Ah r6) {
        AnonymousClass02n<AnonymousClass1Ah> r4 = this.A00;
        if (r4.A01) {
            AnonymousClass02n.A00(r4);
        }
        int i = r4.A00 - 1;
        while (true) {
            if (i < 0) {
                break;
            }
            if (r4.A01) {
                AnonymousClass02n.A00(r4);
            }
            Object[] objArr = r4.A03;
            Object obj = objArr[i];
            if (r6 == obj) {
                Object obj2 = AnonymousClass02n.A04;
                if (obj != obj2) {
                    objArr[i] = obj2;
                    r4.A01 = true;
                }
            } else {
                i--;
            }
        }
        AnonymousClass1B9 remove = this.A01.remove(r6);
        if (remove != null) {
            remove.A00 = 0;
            remove.A02 = null;
            remove.A01 = null;
            AnonymousClass1B9.A03.A8z(remove);
        }
    }

    public final void A03(AnonymousClass1Ah r3, AnonymousClass1BS r4) {
        C000502v<AnonymousClass1Ah, AnonymousClass1B9> r0 = this.A01;
        AnonymousClass1B9 r1 = r0.get(r3);
        if (r1 == null) {
            r1 = AnonymousClass1B9.A00();
            r0.put(r3, r1);
        }
        r1.A01 = r4;
        r1.A00 |= 8;
    }
}
