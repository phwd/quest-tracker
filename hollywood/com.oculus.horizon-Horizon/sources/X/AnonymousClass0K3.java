package X;

/* renamed from: X.0K3  reason: invalid class name */
public class AnonymousClass0K3 implements Comparable<AnonymousClass0K3> {
    public int A00;
    public AnonymousClass0K5 A01;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // java.lang.Comparable
    public final int compareTo(AnonymousClass0K3 r4) {
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
