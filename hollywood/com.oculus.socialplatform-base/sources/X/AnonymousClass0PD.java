package X;

import java.lang.reflect.Method;

/* renamed from: X.0PD  reason: invalid class name */
public final class AnonymousClass0PD extends AbstractC02030hq {
    public static final long serialVersionUID = 1;
    public final Method _factoryMethod;

    @Override // X.AbstractC02030hq
    public final Object A01(String str, AbstractC02210iH r5) throws Exception {
        return this._factoryMethod.invoke(null, str);
    }

    public AnonymousClass0PD(Method method) {
        super(method.getDeclaringClass());
        this._factoryMethod = method;
    }
}
