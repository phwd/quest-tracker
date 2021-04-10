package X;

/* renamed from: X.0Ld  reason: invalid class name */
public final class AnonymousClass0Ld<T> extends AbstractC03080bT<T> implements AbstractC02980bI<T> {
    public final int A00;

    @Override // X.AbstractC03080bT
    public final T A00(AbstractC02990bJ r4) {
        try {
            return (T) AnonymousClass13m.A00(this.A00, r4);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Invalid binding id %d", Integer.valueOf(this.A00)), e);
        }
    }

    public AnonymousClass0Ld(int i, AbstractC02990bJ r2) {
        super(r2);
        this.A00 = i;
    }
}
