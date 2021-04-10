package X;

import java.lang.reflect.Method;

public class Tg extends Zk {
    public final /* synthetic */ Object A00;
    public final /* synthetic */ Method A01;

    public Tg(Method method, Object obj) {
        this.A01 = method;
        this.A00 = obj;
    }

    @Override // X.Zk
    public final <T> T A01(Class<T> cls) throws Exception {
        Zk.A00(cls);
        return (T) this.A01.invoke(this.A00, cls);
    }
}
