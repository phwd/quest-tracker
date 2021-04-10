package X;

import java.lang.reflect.Method;

/* renamed from: X.0ZI  reason: invalid class name */
public class AnonymousClass0ZI extends AbstractC09050zH {
    public final /* synthetic */ Method A00;

    public AnonymousClass0ZI(Method method) {
        this.A00 = method;
    }

    @Override // X.AbstractC09050zH
    public final <T> T A01(Class<T> cls) throws Exception {
        AbstractC09050zH.A00(cls);
        return (T) this.A00.invoke(null, cls, Object.class);
    }
}
