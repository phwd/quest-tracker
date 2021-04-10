package X;

import java.lang.reflect.Method;

/* renamed from: X.0e7  reason: invalid class name */
public class AnonymousClass0e7 extends AnonymousClass148 {
    public final /* synthetic */ Object A00;
    public final /* synthetic */ Method A01;

    public AnonymousClass0e7(Method method, Object obj) {
        this.A01 = method;
        this.A00 = obj;
    }

    @Override // X.AnonymousClass148
    public final <T> T A01(Class<T> cls) throws Exception {
        AnonymousClass148.A00(cls);
        return (T) this.A01.invoke(this.A00, cls);
    }
}
