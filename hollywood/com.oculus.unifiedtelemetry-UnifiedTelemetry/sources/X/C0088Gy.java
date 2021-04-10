package X;

/* renamed from: X.Gy  reason: case insensitive filesystem */
public final class C0088Gy<T> extends Y4<T> {
    public final int A00;

    @Override // X.Y4
    public final T A00(AbstractC0247Xu xu) {
        try {
            return (T) C0515sp.A00(this.A00, xu);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Invalid binding id %d", Integer.valueOf(this.A00)), e);
        }
    }

    public C0088Gy(int i, AbstractC0247Xu xu) {
        super(xu);
        this.A00 = i;
    }
}
