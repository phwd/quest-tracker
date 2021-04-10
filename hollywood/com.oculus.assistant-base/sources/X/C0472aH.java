package X;

/* renamed from: X.aH  reason: case insensitive filesystem */
public final class C0472aH implements Comparable {
    public static final C0471aG A01 = new C0471aG();
    public final int A00;

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        int i = ((C0472aH) obj).A00;
        int i2 = this.A00 ^ Integer.MIN_VALUE;
        int i3 = i ^ Integer.MIN_VALUE;
        if (i2 < i3) {
            return -1;
        }
        if (i2 == i3) {
            return 0;
        }
        return 1;
    }

    public final boolean equals(Object obj) {
        int i = this.A00;
        if (!(obj instanceof C0472aH) || i != ((C0472aH) obj).A00) {
            return false;
        }
        return true;
    }

    public final String toString() {
        return String.valueOf(((long) this.A00) & 4294967295L);
    }

    public /* synthetic */ C0472aH(int i) {
        this.A00 = i;
    }

    public final int hashCode() {
        return this.A00;
    }
}
