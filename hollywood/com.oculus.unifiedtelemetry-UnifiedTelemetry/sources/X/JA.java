package X;

public class JA implements Comparable<JA> {
    public int A00;
    public JC A01;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // java.lang.Comparable
    public final int compareTo(JA ja) {
        int i = this.A00;
        int i2 = ja.A00;
        if (i == i2) {
            return 0;
        }
        if (i > i2) {
            return 1;
        }
        return -1;
    }
}
