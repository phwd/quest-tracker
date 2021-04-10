package X;

import java.lang.reflect.Method;

/* renamed from: X.0Zm  reason: invalid class name and case insensitive filesystem */
public class C02160Zm extends AbstractC09050zH {
    public final /* synthetic */ Object A00;
    public final /* synthetic */ Method A01;

    public C02160Zm(Method method, Object obj) {
        this.A01 = method;
        this.A00 = obj;
    }

    @Override // X.AbstractC09050zH
    public final <T> T A01(Class<T> cls) throws Exception {
        AbstractC09050zH.A00(cls);
        return (T) this.A01.invoke(this.A00, cls);
    }
}
