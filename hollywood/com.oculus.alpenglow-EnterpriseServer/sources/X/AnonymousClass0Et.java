package X;

/* renamed from: X.0Et  reason: invalid class name */
public final class AnonymousClass0Et extends AnonymousClass0LQ {
    public static final long serialVersionUID = 1;

    @Override // X.AnonymousClass0lR
    public final AnonymousClass0lR<Object> A01(Class<?> cls) {
        if (cls == this._scope) {
            return this;
        }
        return new AnonymousClass0Et(cls);
    }

    @Override // X.AnonymousClass0lR, X.AbstractC02680aZ
    public final Object A03(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AnonymousClass0lR
    public final C05800lQ A00(Object obj) {
        return new C05800lQ(getClass(), this._scope, obj);
    }

    public AnonymousClass0Et(Class<?> cls) {
        super(cls);
    }
}
