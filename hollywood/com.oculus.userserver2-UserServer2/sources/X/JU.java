package X;

public class JU implements Comparable<JU> {
    public int A00;
    public JW A01;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // java.lang.Comparable
    public final int compareTo(JU ju) {
        int i = this.A00;
        int i2 = ju.A00;
        if (i == i2) {
            return 0;
        }
        if (i > i2) {
            return 1;
        }
        return -1;
    }
}
