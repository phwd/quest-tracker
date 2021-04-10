package X;

/* renamed from: X.0yV  reason: invalid class name */
public class AnonymousClass0yV<V> implements Comparable<AnonymousClass0yV<V>> {
    public final long A00;
    public final RunnableC08760xm<V> A01;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return Long.valueOf(this.A00).compareTo(Long.valueOf(((AnonymousClass0yV) obj).A00));
    }

    public AnonymousClass0yV(RunnableC08760xm<V> r1, long j) {
        this.A01 = r1;
        this.A00 = j;
    }
}
