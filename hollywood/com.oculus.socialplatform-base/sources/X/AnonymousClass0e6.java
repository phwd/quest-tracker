package X;

import java.lang.reflect.Method;

/* renamed from: X.0e6  reason: invalid class name */
public class AnonymousClass0e6 extends AnonymousClass148 {
    public final /* synthetic */ int A00;
    public final /* synthetic */ Method A01;

    public AnonymousClass0e6(Method method, int i) {
        this.A01 = method;
        this.A00 = i;
    }

    @Override // X.AnonymousClass148
    public final <T> T A01(Class<T> cls) throws Exception {
        AnonymousClass148.A00(cls);
        return (T) this.A01.invoke(null, cls, Integer.valueOf(this.A00));
    }
}
