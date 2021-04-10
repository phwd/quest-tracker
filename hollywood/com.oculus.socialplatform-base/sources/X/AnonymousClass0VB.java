package X;

/* renamed from: X.0VB  reason: invalid class name */
public final class AnonymousClass0VB<T> extends AnonymousClass0mO<T> implements AbstractC03180ld<T> {
    public final int A00;

    @Override // X.AnonymousClass0mO
    public final T A01(AnonymousClass0lg r4) {
        try {
            return (T) AnonymousClass1TK.A00(this.A00, r4, null);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Invalid binding id %d", Integer.valueOf(this.A00)), e);
        }
    }

    public AnonymousClass0VB(int i, AnonymousClass0lg r2) {
        super(r2);
        this.A00 = i;
    }
}
