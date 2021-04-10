package X;

/* renamed from: X.0Gt  reason: invalid class name and case insensitive filesystem */
public final class C00550Gt extends AnonymousClass0Sy {
    public static final long serialVersionUID = 1;

    @Override // X.AbstractC03600nz
    public final AbstractC03600nz<Object> A01(Class<?> cls) {
        if (cls == this._scope) {
            return this;
        }
        return new C00550Gt(cls);
    }

    @Override // X.AnonymousClass0iW, X.AbstractC03600nz
    public final Object A03(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AbstractC03600nz
    public final C03590ny A00(Object obj) {
        return new C03590ny(getClass(), this._scope, obj);
    }

    public C00550Gt(Class<?> cls) {
        super(cls);
    }
}
