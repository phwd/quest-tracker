package X;

import java.io.IOException;
import java.lang.reflect.Method;

public final class dP extends RuntimeException {
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

    public dP(IOException iOException) {
        super(iOException);
        this.lastException = iOException;
    }
}
