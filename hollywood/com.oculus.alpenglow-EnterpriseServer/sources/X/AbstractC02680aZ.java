package X;

/* renamed from: X.0aZ  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC02680aZ<T> extends AnonymousClass0lR<T> {
    public final Class<?> _scope;

    @Override // X.AnonymousClass0lR
    public abstract T A03(Object obj);

    @Override // X.AnonymousClass0lR
    public final Class<?> A02() {
        return this._scope;
    }

    public AbstractC02680aZ(Class<?> cls) {
        this._scope = cls;
    }

    @Override // X.AnonymousClass0lR
    public boolean A04(AnonymousClass0lR<?> r4) {
        if (r4.getClass() == getClass() && r4.A02() == this._scope) {
            return true;
        }
        return false;
    }
}
