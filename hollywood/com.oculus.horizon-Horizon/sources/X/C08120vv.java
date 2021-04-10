package X;

import java.io.IOException;
import java.lang.reflect.Method;

/* renamed from: X.0vv  reason: invalid class name and case insensitive filesystem */
public final class C08120vv extends RuntimeException {
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

    public C08120vv(IOException iOException) {
        super(iOException);
        this.lastException = iOException;
    }
}
