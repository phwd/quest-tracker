package X;

/* renamed from: X.1XC  reason: invalid class name */
public class AnonymousClass1XC<V> implements Comparable<AnonymousClass1XC<V>> {
    public final long A00;
    public final AnonymousClass1X8<V> A01;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return Long.valueOf(this.A00).compareTo(Long.valueOf(((AnonymousClass1XC) obj).A00));
    }

    public AnonymousClass1XC(AnonymousClass1X8<V> r1, long j) {
        this.A01 = r1;
        this.A00 = j;
    }
}
