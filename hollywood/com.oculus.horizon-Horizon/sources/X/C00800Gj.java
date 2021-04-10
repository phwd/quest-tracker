package X;

import java.lang.reflect.Constructor;

/* renamed from: X.0Gj  reason: invalid class name and case insensitive filesystem */
public final class C00800Gj extends AbstractC03840gD {
    public static final long serialVersionUID = 1;
    public final Constructor<?> _ctor;

    @Override // X.AbstractC03840gD
    public final Object A01(String str, AbstractC04020gg r5) throws Exception {
        return this._ctor.newInstance(str);
    }

    public C00800Gj(Constructor<?> constructor) {
        super(constructor.getDeclaringClass());
        this._ctor = constructor;
    }
}
