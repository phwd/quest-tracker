package X;

import java.lang.reflect.Constructor;

/* renamed from: X.0PV  reason: invalid class name */
public final class AnonymousClass0PV extends AbstractC02030hq {
    public static final long serialVersionUID = 1;
    public final Constructor<?> _ctor;

    @Override // X.AbstractC02030hq
    public final Object A01(String str, AbstractC02210iH r4) throws Exception {
        return this._ctor.newInstance(str);
    }

    public AnonymousClass0PV(Constructor<?> constructor) {
        super(constructor.getDeclaringClass());
        this._ctor = constructor;
    }
}
