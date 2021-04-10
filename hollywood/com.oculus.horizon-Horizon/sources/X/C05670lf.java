package X;

import java.io.Serializable;

/* renamed from: X.0lf  reason: invalid class name and case insensitive filesystem */
public final class C05670lf implements Serializable {
    public static final C05670lf A00 = new C05670lf();
    public static final long serialVersionUID = 1;

    /* JADX WARN: Incorrect types in method signature: (Ljava/lang/Class<*>;Ljava/lang/String;)Z */
    public static boolean A00(C05670lf r7, Class cls) {
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
                    if (!A00(r7, cls2)) {
                    }
                }
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Incorrect types in method signature: (Ljava/lang/Class<*>;Ljava/lang/String;)Z */
    public static boolean A01(C05670lf r7, Class cls) {
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
                    if (!A01(r7, cls2)) {
                    }
                }
                return false;
            }
        }
        return true;
    }
}
