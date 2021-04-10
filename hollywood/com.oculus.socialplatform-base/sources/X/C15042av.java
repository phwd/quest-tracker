package X;

/* renamed from: X.2av  reason: invalid class name and case insensitive filesystem */
public final class C15042av {
    public static boolean[] A00 = new boolean[3];

    public static void A00(C14932ab r6, C15022am r7, AnonymousClass2ac r8) {
        r8.A0C = -1;
        r8.A0P = -1;
        Integer num = r6.A0w[0];
        Integer num2 = AnonymousClass007.A01;
        if (num != num2 && r8.A0w[0] == AnonymousClass007.A04) {
            C14982ai r1 = r8.A0a;
            int i = r1.A02;
            int A04 = r6.A04() - r8.A0b.A02;
            r1.A03 = r7.A08(r1);
            C14982ai r12 = r8.A0b;
            r12.A03 = r7.A08(r12);
            r7.A0C(r8.A0a.A03, i);
            r7.A0C(r8.A0b.A03, A04);
            r8.A0C = 2;
            r8.A0S = i;
            int i2 = A04 - i;
            r8.A0R = i2;
            int i3 = r8.A0M;
            if (i2 < i3) {
                r8.A0R = i3;
            }
        }
        if (r6.A0w[1] != num2 && r8.A0w[1] == AnonymousClass007.A04) {
            C14982ai r13 = r8.A0c;
            int i4 = r13.A02;
            int A03 = r6.A03() - r8.A0W.A02;
            r13.A03 = r7.A08(r13);
            C14982ai r14 = r8.A0W;
            r14.A03 = r7.A08(r14);
            r7.A0C(r8.A0c.A03, i4);
            r7.A0C(r8.A0W.A03, A03);
            if (r8.A08 > 0 || r8.A0Q == 8) {
                C14982ai r15 = r8.A0V;
                r15.A03 = r7.A08(r15);
                r7.A0C(r8.A0V.A03, r8.A08 + i4);
            }
            r8.A0P = 2;
            r8.A0T = i4;
            int i5 = A03 - i4;
            r8.A0A = i5;
            int i6 = r8.A0L;
            if (i5 < i6) {
                r8.A0A = i6;
            }
        }
    }
}
