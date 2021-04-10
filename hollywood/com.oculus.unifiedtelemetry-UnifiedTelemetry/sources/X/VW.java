package X;

import java.io.Serializable;

public final class VW implements Serializable {
    public static final VW A00 = new VW();
    public static final long serialVersionUID = 1;

    /* JADX WARN: Incorrect types in method signature: (Ljava/lang/Class<*>;Ljava/lang/String;)Z */
    public static boolean A00(VW vw, Class cls) {
        Class<?>[] interfaces = cls.getInterfaces();
        int length = interfaces.length;
        int i = 0;
        while (true) {
            if (i < length) {
                if (interfaces[i].getName().equals("org.w3c.dom.Node")) {
                    break;
                }
                i++;
            } else {
                for (Class<?> cls2 : interfaces) {
                    if (!A00(vw, cls2)) {
                    }
                }
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Incorrect types in method signature: (Ljava/lang/Class<*>;Ljava/lang/String;)Z */
    public static boolean A01(VW vw, Class cls) {
        Class<?>[] interfaces = cls.getInterfaces();
        int length = interfaces.length;
        int i = 0;
        while (true) {
            if (i < length) {
                if (interfaces[i].getName().startsWith("javax.xml.")) {
                    break;
                }
                i++;
            } else {
                for (Class<?> cls2 : interfaces) {
                    if (!A01(vw, cls2)) {
                    }
                }
                return false;
            }
        }
        return true;
    }
}
