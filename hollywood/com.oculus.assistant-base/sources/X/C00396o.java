package X;

/* renamed from: X.6o  reason: invalid class name and case insensitive filesystem */
public final class C00396o extends AnonymousClass0V {
    public final long A00;

    public final boolean equals(Object obj) {
        if (obj != this) {
            return obj != null && obj.getClass() == getClass() && ((C00396o) obj).A00 == this.A00;
        }
        return true;
    }

    public final int hashCode() {
        long j = this.A00;
        return ((int) j) ^ ((int) (j >> 32));
    }

    public C00396o(long j) {
        this.A00 = j;
    }
}
