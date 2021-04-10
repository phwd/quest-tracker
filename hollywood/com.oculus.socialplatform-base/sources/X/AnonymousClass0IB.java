package X;

/* renamed from: X.0IB  reason: invalid class name */
public class AnonymousClass0IB implements Comparable<AnonymousClass0IB> {
    public int A00;
    public AnonymousClass0IE A01;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // java.lang.Comparable
    public final int compareTo(AnonymousClass0IB r4) {
        int i = this.A00;
        int i2 = r4.A00;
        if (i == i2) {
            return 0;
        }
        if (i > i2) {
            return 1;
        }
        return -1;
    }
}
