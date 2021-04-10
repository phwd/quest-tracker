package X;

/* renamed from: X.0Iw  reason: invalid class name and case insensitive filesystem */
public final class C01020Iw<T> extends AbstractC06650pP<T> {
    public final int A00;

    @Override // X.AbstractC06650pP
    public final T A00(AbstractC06640p5 r5) {
        try {
            return (T) AnonymousClass117.A00(this.A00, r5);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Invalid binding id %d", Integer.valueOf(this.A00)), e);
        }
    }

    public C01020Iw(int i, AbstractC06640p5 r2) {
        super(r2);
        this.A00 = i;
    }
}
