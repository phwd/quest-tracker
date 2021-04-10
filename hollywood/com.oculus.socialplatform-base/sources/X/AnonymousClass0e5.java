package X;

import java.lang.reflect.Method;

/* renamed from: X.0e5  reason: invalid class name */
public class AnonymousClass0e5 extends AnonymousClass148 {
    public final /* synthetic */ Method A00;

    public AnonymousClass0e5(Method method) {
        this.A00 = method;
    }

    @Override // X.AnonymousClass148
    public final <T> T A01(Class<T> cls) throws Exception {
        AnonymousClass148.A00(cls);
        return (T) this.A00.invoke(null, cls, Object.class);
    }
}
