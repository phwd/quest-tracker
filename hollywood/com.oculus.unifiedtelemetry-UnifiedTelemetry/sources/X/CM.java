package X;

import java.lang.reflect.Method;

public final class CM extends WN {
    public static final long serialVersionUID = 1;
    public final Method _factoryMethod;

    @Override // X.WN
    public final Object A01(String str, AbstractC0226Wn wn) throws Exception {
        return this._factoryMethod.invoke(null, str);
    }

    public CM(Method method) {
        super(method.getDeclaringClass());
        this._factoryMethod = method;
    }
}
