package X;

/* renamed from: X.aK  reason: case insensitive filesystem */
public final class C0475aK implements Comparable {
    public static final C0474aJ A01 = new C0474aJ();
    public final long A00;

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return ((this.A00 ^ Long.MIN_VALUE) > (((C0475aK) obj).A00 ^ Long.MIN_VALUE) ? 1 : ((this.A00 ^ Long.MIN_VALUE) == (((C0475aK) obj).A00 ^ Long.MIN_VALUE) ? 0 : -1));
    }

    public final boolean equals(Object obj) {
        long j = this.A00;
        if (!(obj instanceof C0475aK) || j != ((C0475aK) obj).A00) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        long j = this.A00;
        return (int) (j ^ (j >>> 32));
    }

    public final String toString() {
        long j = this.A00;
        if (j >= 0) {
            String l = Long.toString(j, 10);
            C0514bB.A01(l, "java.lang.Long.toString(this, checkRadix(radix))");
            return l;
        }
        long j2 = (long) 10;
        long j3 = ((j >>> 1) / j2) << 1;
        long j4 = j - (j3 * j2);
        if (j4 >= j2) {
            j4 -= j2;
            j3++;
        }
        String l2 = Long.toString(j3, 10);
        C0514bB.A01(l2, "java.lang.Long.toString(this, checkRadix(radix))");
        String l3 = Long.toString(j4, 10);
        C0514bB.A01(l3, "java.lang.Long.toString(this, checkRadix(radix))");
        return AnonymousClass08.A04(l2, l3);
    }

    public /* synthetic */ C0475aK(long j) {
        this.A00 = j;
    }
}
