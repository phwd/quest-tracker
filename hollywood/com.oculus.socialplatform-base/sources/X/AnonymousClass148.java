package X;

import java.lang.reflect.Modifier;

/* renamed from: X.148  reason: invalid class name */
public abstract class AnonymousClass148 {
    public abstract <T> T A01(Class<T> cls) throws Exception;

    public static void A00(Class<?> cls) {
        int modifiers = cls.getModifiers();
        if (Modifier.isInterface(modifiers)) {
            throw new UnsupportedOperationException(AnonymousClass006.A07("Interface can't be instantiated! Interface name: ", cls.getName()));
        } else if (Modifier.isAbstract(modifiers)) {
            throw new UnsupportedOperationException(AnonymousClass006.A07("Abstract class can't be instantiated! Class name: ", cls.getName()));
        }
    }
}
