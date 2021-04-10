package X;

/* renamed from: X.08y  reason: invalid class name and case insensitive filesystem */
public final class C003008y<T> extends AnonymousClass0J6<T> {
    public final int A00;

    @Override // X.AnonymousClass0J6
    public final T A00(AbstractC06640p5 r5) {
        try {
            return (T) AnonymousClass117.A00(this.A00, r5);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Invalid binding id %d", Integer.valueOf(this.A00)), e);
        }
    }

    public C003008y(int i, AbstractC06640p5 r2) {
        super(r2);
        this.A00 = i;
    }
}
