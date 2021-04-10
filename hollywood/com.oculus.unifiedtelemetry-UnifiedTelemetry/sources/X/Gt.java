package X;

public final class Gt<T> extends Y4<T> implements AbstractC0246Xt<T> {
    public final int A00;

    @Override // X.Y4
    public final T A00(AbstractC0247Xu xu) {
        try {
            return (T) C0515sp.A00(this.A00, xu);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Invalid binding id %d", Integer.valueOf(this.A00)), e);
        }
    }

    public Gt(int i, AbstractC0247Xu xu) {
        super(xu);
        this.A00 = i;
    }
}
