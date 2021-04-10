package X;

public final class Dc extends AbstractC0493ad {
    public int A00;
    public final short[] A01;

    public Dc(short[] sArr) {
        C0514bB.A02(sArr, "array");
        this.A01 = sArr;
    }

    public final boolean hasNext() {
        if (this.A00 < this.A01.length) {
            return true;
        }
        return false;
    }
}
