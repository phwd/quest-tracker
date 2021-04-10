package X;

import java.lang.reflect.Method;

/* renamed from: X.0X5  reason: invalid class name */
public class AnonymousClass0X5 extends AnonymousClass0EF {
    public final /* synthetic */ int A00;
    public final /* synthetic */ Method A01;

    public AnonymousClass0X5(Method method, int i) {
        this.A01 = method;
        this.A00 = i;
    }

    @Override // X.AnonymousClass0EF
    public final <T> T A01(Class<T> cls) throws Exception {
        AnonymousClass0EF.A00(cls);
        return (T) this.A01.invoke(null, cls, Integer.valueOf(this.A00));
    }
}
