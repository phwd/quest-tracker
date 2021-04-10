package X;

import java.io.IOException;
import java.lang.reflect.Method;

/* renamed from: X.c6  reason: case insensitive filesystem */
public final class C0568c6 extends RuntimeException {
    public static final Method A00;
    public IOException lastException;

    static {
        Method method;
        try {
            method = Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class);
        } catch (Exception unused) {
            method = null;
        }
        A00 = method;
    }

    public C0568c6(IOException iOException) {
        super(iOException);
        this.lastException = iOException;
    }
}
