package X;

import java.io.IOException;
import java.lang.reflect.Method;

public final class X3 extends RuntimeException {
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

    public X3(IOException iOException) {
        super(iOException);
        this.lastException = iOException;
    }
}
