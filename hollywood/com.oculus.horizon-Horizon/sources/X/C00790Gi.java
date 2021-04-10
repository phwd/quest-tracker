package X;

import java.lang.reflect.Method;

/* renamed from: X.0Gi  reason: invalid class name and case insensitive filesystem */
public final class C00790Gi extends AbstractC03840gD {
    public static final long serialVersionUID = 1;
    public final Method _factoryMethod;

    @Override // X.AbstractC03840gD
    public final Object A01(String str, AbstractC04020gg r5) throws Exception {
        return this._factoryMethod.invoke(null, str);
    }

    public C00790Gi(Method method) {
        super(method.getDeclaringClass());
        this._factoryMethod = method;
    }
}
