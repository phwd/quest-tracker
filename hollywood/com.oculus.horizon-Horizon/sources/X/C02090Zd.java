package X;

import java.lang.reflect.Method;

/* renamed from: X.0Zd  reason: invalid class name and case insensitive filesystem */
public class C02090Zd extends AbstractC09050zH {
    public final /* synthetic */ int A00;
    public final /* synthetic */ Method A01;

    public C02090Zd(Method method, int i) {
        this.A01 = method;
        this.A00 = i;
    }

    @Override // X.AbstractC09050zH
    public final <T> T A01(Class<T> cls) throws Exception {
        AbstractC09050zH.A00(cls);
        return (T) this.A01.invoke(null, cls, Integer.valueOf(this.A00));
    }
}
