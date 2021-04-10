package X;

/* renamed from: X.0XF  reason: invalid class name */
public class AnonymousClass0XF<V> implements Comparable<AnonymousClass0XF<V>> {
    public final long A00;
    public final RunnableC002708s<V> A01;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return Long.valueOf(this.A00).compareTo(Long.valueOf(((AnonymousClass0XF) obj).A00));
    }

    public AnonymousClass0XF(RunnableC002708s<V> r1, long j) {
        this.A01 = r1;
        this.A00 = j;
    }
}
