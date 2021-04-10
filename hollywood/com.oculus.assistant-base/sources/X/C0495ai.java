package X;

import java.io.Closeable;
import java.lang.reflect.Method;

/* renamed from: X.ai  reason: case insensitive filesystem */
public final class C0495ai {
    public static final void A00(Closeable closeable, Throwable th) {
        Method method;
        if (closeable == null) {
            return;
        }
        if (th == null) {
            closeable.close();
            return;
        }
        try {
            closeable.close();
        } catch (Throwable th2) {
            if (th != th2 && (method = C0494af.A00) != null) {
                method.invoke(th, th2);
            }
        }
    }
}
