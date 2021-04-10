package X;

/* renamed from: X.0iW  reason: invalid class name */
public abstract class AnonymousClass0iW<T> extends AbstractC03600nz<T> {
    public final Class<?> _scope;

    @Override // X.AbstractC03600nz
    public abstract T A03(Object obj);

    public AnonymousClass0iW(Class<?> cls) {
        this._scope = cls;
    }

    @Override // X.AbstractC03600nz
    public final Class<?> A02() {
        return this._scope;
    }

    @Override // X.AbstractC03600nz
    public boolean A04(AbstractC03600nz<?> r4) {
        if (r4.getClass() == getClass() && r4.A02() == this._scope) {
            return true;
        }
        return false;
    }
}
