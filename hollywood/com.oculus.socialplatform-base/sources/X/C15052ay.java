package X;

import java.util.ArrayList;

/* renamed from: X.2ay  reason: invalid class name and case insensitive filesystem */
public final class C15052ay {
    public static int A03;
    public AbstractC14992aj A00;
    public AbstractC14992aj A01 = null;
    public ArrayList<AbstractC14992aj> A02 = new ArrayList<>();

    public static long A00(C15052ay r8, AnonymousClass2an r9, long j) {
        AbstractC14992aj r4 = r9.A02;
        if (r4 instanceof AnonymousClass2aq) {
            return j;
        }
        int size = r9.A07.size();
        long j2 = j;
        for (int i = 0; i < size; i++) {
            AnonymousClass2bJ r7 = r9.A07.get(i);
            if (r7 instanceof AnonymousClass2an) {
                AnonymousClass2an r72 = (AnonymousClass2an) r7;
                if (r72.A02 != r4) {
                    j2 = Math.min(j2, A00(r8, r72, ((long) r72.A03) + j));
                }
            }
        }
        if (r9 != r4.A03) {
            return j2;
        }
        long A05 = j - r4.A05();
        return Math.min(Math.min(j2, A00(r8, r4.A04, A05)), A05 - ((long) r4.A04.A03));
    }

    public static long A01(C15052ay r8, AnonymousClass2an r9, long j) {
        AbstractC14992aj r4 = r9.A02;
        if (r4 instanceof AnonymousClass2aq) {
            return j;
        }
        int size = r9.A07.size();
        long j2 = j;
        for (int i = 0; i < size; i++) {
            AnonymousClass2bJ r7 = r9.A07.get(i);
            if (r7 instanceof AnonymousClass2an) {
                AnonymousClass2an r72 = (AnonymousClass2an) r7;
                if (r72.A02 != r4) {
                    j2 = Math.max(j2, A01(r8, r72, ((long) r72.A03) + j));
                }
            }
        }
        if (r9 != r4.A04) {
            return j2;
        }
        long A05 = j + r4.A05();
        return Math.max(Math.max(j2, A01(r8, r4.A03, A05)), A05 - ((long) r4.A03.A03));
    }

    public C15052ay(AbstractC14992aj r2) {
        A03++;
        this.A00 = r2;
        this.A01 = r2;
    }
}
