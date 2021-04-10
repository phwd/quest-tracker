package X;

import java.lang.reflect.Method;

/* renamed from: X.af  reason: case insensitive filesystem */
public final class C0494af {
    public static final Method A00;
    public static final Method A01;
    public static final C0494af A02 = new C0494af();

    static {
        Method method;
        Method method2;
        Class<?> cls;
        Method[] methods = Throwable.class.getMethods();
        C0514bB.A01(methods, "throwableMethods");
        int length = methods.length;
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                method = null;
                break;
            }
            method = methods[i2];
            C0514bB.A01(method, "it");
            if (C0514bB.A05(method.getName(), "addSuppressed")) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                C0514bB.A01(parameterTypes, "it.parameterTypes");
                C0514bB.A02(parameterTypes, "$this$singleOrNull");
                if (parameterTypes.length == 1) {
                    cls = parameterTypes[0];
                } else {
                    cls = null;
                }
                if (C0514bB.A05(cls, Throwable.class)) {
                    break;
                }
            }
            i2++;
        }
        A00 = method;
        while (true) {
            if (i >= length) {
                method2 = null;
                break;
            }
            method2 = methods[i];
            C0514bB.A01(method2, "it");
            if (C0514bB.A05(method2.getName(), "getSuppressed")) {
                break;
            }
            i++;
        }
        A01 = method2;
    }
}
