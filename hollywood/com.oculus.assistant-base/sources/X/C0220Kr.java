package X;

/* renamed from: X.Kr  reason: case insensitive filesystem */
public final class C0220Kr extends AbstractC0491ab {
    public int A00;
    public final int[] A01;

    public C0220Kr(int[] iArr) {
        C0514bB.A02(iArr, "array");
        this.A01 = iArr;
    }

    public final boolean hasNext() {
        if (this.A00 < this.A01.length) {
            return true;
        }
        return false;
    }
}
