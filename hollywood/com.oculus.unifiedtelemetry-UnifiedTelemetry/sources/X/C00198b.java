package X;

/* renamed from: X.8b  reason: invalid class name and case insensitive filesystem */
public final class C00198b<T> extends I1<T> {
    public final int A00;

    @Override // X.I1
    public final T A00(AbstractC0247Xu xu) {
        try {
            return (T) C0515sp.A00(this.A00, xu);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Invalid binding id %d", Integer.valueOf(this.A00)), e);
        }
    }

    public C00198b(int i, AbstractC0247Xu xu) {
        super(xu);
        this.A00 = i;
    }
}
