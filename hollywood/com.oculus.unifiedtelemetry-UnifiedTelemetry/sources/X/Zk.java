package X;

import java.lang.reflect.Modifier;

public abstract class Zk {
    public abstract <T> T A01(Class<T> cls) throws Exception;

    public static void A00(Class<?> cls) {
        String str;
        int modifiers = cls.getModifiers();
        if (Modifier.isInterface(modifiers)) {
            str = "Interface can't be instantiated! Interface name: ";
        } else if (Modifier.isAbstract(modifiers)) {
            str = "Abstract class can't be instantiated! Class name: ";
        } else {
            return;
        }
        throw new UnsupportedOperationException(AnonymousClass06.A04(str, cls.getName()));
    }
}
