package X;

/* renamed from: X.Ys  reason: case insensitive filesystem */
public final class C0448Ys implements Comparable {
    public AbstractC0447Yr A00;
    public int A01;
    public final /* synthetic */ C1413yz A02;

    public C0448Ys(C1413yz yzVar, AbstractC0447Yr yr, int i) {
        this.A02 = yzVar;
        this.A00 = yr;
        this.A01 = i;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        int i = this.A01;
        int i2 = ((C0448Ys) obj).A01;
        if (i < i2) {
            return -1;
        }
        if (i > i2) {
            return 1;
        }
        return 0;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C0448Ys) {
            return this.A00.equals(((C0448Ys) obj).A00);
        }
        return super.equals(obj);
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }
}
