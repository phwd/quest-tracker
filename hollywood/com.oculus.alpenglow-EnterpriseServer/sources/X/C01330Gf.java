package X;

/* renamed from: X.0Gf  reason: invalid class name and case insensitive filesystem */
public final class C01330Gf<T> extends AbstractC01760Ll<T> {
    public final int A00;

    @Override // X.AbstractC01760Ll
    public final T A00(AbstractC02990bJ r4) {
        try {
            return (T) AnonymousClass13m.A00(this.A00, r4);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Invalid binding id %d", Integer.valueOf(this.A00)), e);
        }
    }

    public C01330Gf(int i, AbstractC02990bJ r2) {
        super(r2);
        this.A00 = i;
    }
}
