package X;

public final class Fb extends AbstractC0492ac {
    public int A00;
    public final long[] A01;

    public Fb(long[] jArr) {
        C0514bB.A02(jArr, "array");
        this.A01 = jArr;
    }

    public final boolean hasNext() {
        if (this.A00 < this.A01.length) {
            return true;
        }
        return false;
    }
}
