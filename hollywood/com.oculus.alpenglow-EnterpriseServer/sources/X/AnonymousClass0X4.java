package X;

import java.lang.reflect.Method;

/* renamed from: X.0X4  reason: invalid class name */
public class AnonymousClass0X4 extends AnonymousClass0EF {
    public final /* synthetic */ Method A00;

    public AnonymousClass0X4(Method method) {
        this.A00 = method;
    }

    @Override // X.AnonymousClass0EF
    public final <T> T A01(Class<T> cls) throws Exception {
        AnonymousClass0EF.A00(cls);
        return (T) this.A00.invoke(null, cls, Object.class);
    }
}
