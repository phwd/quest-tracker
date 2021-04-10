package X;

/* renamed from: X.08K  reason: invalid class name */
public abstract class AnonymousClass08K extends AbstractC04160gv<Object> {
    public static final long f$0 = 1;
    public static final long serialVersionUID = 1;

    @Override // X.AbstractC04750jT
    public final AbstractC04750jT<Object> A01(Class<?> cls) {
        if (cls == this._scope) {
            return this;
        }
        return new AnonymousClass08K(cls);
    }

    @Override // X.AbstractC04750jT
    public final C04740jS A00(Object obj) {
        return new C04740jS(getClass(), this._scope, obj);
    }

    public AnonymousClass08K(Class<?> cls) {
        super(cls);
    }
}
