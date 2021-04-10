package X;

import java.lang.reflect.Method;

/* renamed from: X.0X6  reason: invalid class name */
public class AnonymousClass0X6 extends AnonymousClass0EF {
    public final /* synthetic */ Object A00;
    public final /* synthetic */ Method A01;

    public AnonymousClass0X6(Method method, Object obj) {
        this.A01 = method;
        this.A00 = obj;
    }

    @Override // X.AnonymousClass0EF
    public final <T> T A01(Class<T> cls) throws Exception {
        AnonymousClass0EF.A00(cls);
        return (T) this.A01.invoke(this.A00, cls);
    }
}
