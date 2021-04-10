package X;

import java.lang.reflect.Method;

/* renamed from: X.Tf  reason: case insensitive filesystem */
public class C0167Tf extends Zk {
    public final /* synthetic */ int A00;
    public final /* synthetic */ Method A01;

    public C0167Tf(Method method, int i) {
        this.A01 = method;
        this.A00 = i;
    }

    @Override // X.Zk
    public final <T> T A01(Class<T> cls) throws Exception {
        Zk.A00(cls);
        return (T) this.A01.invoke(null, cls, Integer.valueOf(this.A00));
    }
}
