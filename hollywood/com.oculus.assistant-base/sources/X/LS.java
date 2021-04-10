package X;

import java.lang.reflect.Method;

public final class LS {
    public static Method A02;
    public final LR A00;
    public final LR A01;

    static {
        try {
            A02 = Class.forName("org.slf4j.LoggerFactory").getDeclaredMethod("getLogger", String.class);
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
        }
    }

    public LS(LR lr, LR lr2) {
        this.A00 = lr;
        this.A01 = lr2;
    }
}
