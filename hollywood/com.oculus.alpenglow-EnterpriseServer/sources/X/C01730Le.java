package X;

/* renamed from: X.0Le  reason: invalid class name and case insensitive filesystem */
public final class C01730Le<T> extends AbstractC03080bT<T> {
    public final int A00;

    @Override // X.AbstractC03080bT
    public final T A00(AbstractC02990bJ r4) {
        try {
            return (T) AnonymousClass13m.A00(this.A00, r4);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Invalid binding id %d", Integer.valueOf(this.A00)), e);
        }
    }

    public C01730Le(int i, AbstractC02990bJ r2) {
        super(r2);
        this.A00 = i;
    }
}
