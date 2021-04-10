package X;

public final class Br implements Comparable {
    public int A00;
    public AbstractC0123Bt A01;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        int i = this.A00;
        int i2 = ((Br) obj).A00;
        if (i == i2) {
            return 0;
        }
        if (i > i2) {
            return 1;
        }
        return -1;
    }
}
