package X;

import com.google.common.annotations.VisibleForTesting;
import java.io.Closeable;
import java.lang.reflect.Method;

@VisibleForTesting
public final class UQ implements AnonymousClass9Y {
    public static final UQ A00 = new UQ();
    public static final Method A01;

    static {
        Method method;
        try {
            method = Throwable.class.getMethod("addSuppressed", Throwable.class);
        } catch (Throwable unused) {
            method = null;
        }
        A01 = method;
    }

    @Override // X.AnonymousClass9Y
    public final void A5S(Closeable closeable, Throwable th, Throwable th2) {
        if (th != th2) {
            try {
                A01.invoke(th, th2);
            } catch (Throwable unused) {
                UR.A00.A5S(closeable, th, th2);
            }
        }
    }
}
