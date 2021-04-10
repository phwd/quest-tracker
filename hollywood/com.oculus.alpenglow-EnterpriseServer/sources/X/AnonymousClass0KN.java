package X;

import java.lang.reflect.Constructor;

/* renamed from: X.0KN  reason: invalid class name */
public final class AnonymousClass0KN extends AbstractC02490Zv {
    public static final long serialVersionUID = 1;
    public final Constructor<?> _ctor;

    @Override // X.AbstractC02490Zv
    public final Object A01(String str, AbstractC02570aK r4) throws Exception {
        return this._ctor.newInstance(str);
    }

    public AnonymousClass0KN(Constructor<?> constructor) {
        super(constructor.getDeclaringClass());
        this._ctor = constructor;
    }
}
