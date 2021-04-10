package X;

import java.io.Serializable;

public final class P8 implements Serializable {
    public static final P8 A00 = new P8();
    public static final long serialVersionUID = 1;

    public static boolean A00(P8 p8, Class cls) {
        while (cls != null) {
            if (cls.getName().equals("org.w3c.dom.Node") || p8.A02(cls)) {
                return true;
            }
            cls = cls.getSuperclass();
        }
        return false;
    }

    public static boolean A01(P8 p8, Class cls) {
        Class superclass = cls.getSuperclass();
        while (true) {
            if (superclass != null) {
                if (superclass.getName().startsWith("javax.xml.")) {
                    break;
                }
                superclass = superclass.getSuperclass();
            } else {
                while (!p8.A03(cls)) {
                    cls = cls.getSuperclass();
                    if (cls == null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

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
