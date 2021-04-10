package X;

/* renamed from: X.89  reason: invalid class name */
public abstract class AnonymousClass89 extends X2<Object> {
    public static final long f$0 = 1;
    public static final long serialVersionUID = 1;

    @Override // X.pp
    public final pp<Object> A01(Class<?> cls) {
        if (cls == this._scope) {
            return this;
        }
        return new AnonymousClass89(cls);
    }

    @Override // X.pp
    public final po A00(Object obj) {
        return new po(getClass(), this._scope, obj);
    }

    public AnonymousClass89(Class<?> cls) {
        super(cls);
    }
}
