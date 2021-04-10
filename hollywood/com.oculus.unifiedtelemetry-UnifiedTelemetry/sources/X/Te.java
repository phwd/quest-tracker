package X;

import java.lang.reflect.Method;

public class Te extends Zk {
    public final /* synthetic */ Method A00;

    public Te(Method method) {
        this.A00 = method;
    }

    @Override // X.Zk
    public final <T> T A01(Class<T> cls) throws Exception {
        Zk.A00(cls);
        return (T) this.A00.invoke(null, cls, Object.class);
    }
}
