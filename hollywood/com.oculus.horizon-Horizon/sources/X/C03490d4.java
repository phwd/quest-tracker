package X;

import com.google.common.annotations.VisibleForTesting;
import java.io.Closeable;
import java.lang.reflect.Method;

@VisibleForTesting
/* renamed from: X.0d4  reason: invalid class name and case insensitive filesystem */
public final class C03490d4 implements AnonymousClass0u5 {
    public static final C03490d4 A00 = new C03490d4();
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

    @Override // X.AnonymousClass0u5
    public final void A9b(Closeable closeable, Throwable th, Throwable th2) {
        if (th != th2) {
            try {
                A01.invoke(th, th2);
            } catch (Throwable unused) {
                C03500d5.A00.A9b(closeable, th, th2);
            }
        }
    }
}
