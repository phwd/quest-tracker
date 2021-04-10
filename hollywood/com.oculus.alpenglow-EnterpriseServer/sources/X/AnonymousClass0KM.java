package X;

import java.lang.reflect.Method;

/* renamed from: X.0KM  reason: invalid class name */
public final class AnonymousClass0KM extends AbstractC02490Zv {
    public static final long serialVersionUID = 1;
    public final Method _factoryMethod;

    @Override // X.AbstractC02490Zv
    public final Object A01(String str, AbstractC02570aK r5) throws Exception {
        return this._factoryMethod.invoke(null, str);
    }

    public AnonymousClass0KM(Method method) {
        super(method.getDeclaringClass());
        this._factoryMethod = method;
    }
}
