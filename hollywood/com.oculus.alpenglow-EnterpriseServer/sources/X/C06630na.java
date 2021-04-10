package X;

import java.io.Serializable;

/* renamed from: X.0na  reason: invalid class name and case insensitive filesystem */
public final class C06630na implements Serializable {
    public static final C06630na A00 = new C06630na();
    public static final long serialVersionUID = 1;

    /* JADX WARN: Incorrect types in method signature: (Ljava/lang/Class<*>;Ljava/lang/String;)Z */
    public static boolean A00(C06630na r3, Class cls) {
        while (cls != null) {
            if (cls.getName().equals("org.w3c.dom.Node") || r3.A02(cls)) {
                return true;
            }
            cls = cls.getSuperclass();
        }
        return false;
    }

    /* JADX WARN: Incorrect types in method signature: (Ljava/lang/Class<*>;Ljava/lang/String;)Z */
    public static boolean A01(C06630na r4, Class cls) {
        Class superclass = cls.getSuperclass();
        while (true) {
            if (superclass != null) {
                if (superclass.getName().startsWith("javax.xml.")) {
                    break;
                }
                superclass = superclass.getSuperclass();
            } else {
                while (!r4.A03(cls)) {
                    cls = cls.getSuperclass();
                    if (cls == null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;Ljava/lang/String;)Z */
    private boolean A02(Class cls) {
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
                    if (!A02(cls2)) {
                    }
                }
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;Ljava/lang/String;)Z */
    private boolean A03(Class cls) {
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
                    if (!A03(cls2)) {
                    }
                }
                return false;
            }
        }
        return true;
    }
}
