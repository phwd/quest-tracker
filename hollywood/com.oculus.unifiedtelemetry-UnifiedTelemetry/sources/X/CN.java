package X;

import java.lang.reflect.Constructor;

public final class CN extends WN {
    public static final long serialVersionUID = 1;
    public final Constructor<?> _ctor;

    @Override // X.WN
    public final Object A01(String str, AbstractC0226Wn wn) throws Exception {
        return this._ctor.newInstance(str);
    }

    public CN(Constructor<?> constructor) {
        super(constructor.getDeclaringClass());
        this._ctor = constructor;
    }
}
