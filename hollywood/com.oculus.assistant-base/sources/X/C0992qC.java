package X;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: X.qC  reason: case insensitive filesystem */
public final class C0992qC extends LQ {
    public static final LS A02;
    public InputStream A00 = null;
    public OutputStream A01 = null;

    static {
        LR lr;
        LR lr2;
        String name = C0992qC.class.getName();
        try {
            Object invoke = LS.A02.invoke(null, name);
            Class<?> cls = invoke.getClass();
            Method declaredMethod = cls.getDeclaredMethod("error", String.class);
            Method declaredMethod2 = cls.getDeclaredMethod("warn", String.class);
            lr = new C0994qE(declaredMethod, invoke);
            lr2 = new C0995qF(declaredMethod2, invoke);
        } catch (ExceptionInInitializerError | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | NullPointerException | InvocationTargetException unused) {
            lr = new C0996qG();
            lr2 = new C0997qH();
        }
        A02 = new LS(lr, lr2);
    }

    public C0992qC() {
    }

    public C0992qC(InputStream inputStream) {
        this.A00 = inputStream;
    }

    public C0992qC(OutputStream outputStream) {
        this.A01 = outputStream;
    }
}
