package sun.reflect.misc;

import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import sun.reflect.Reflection;

public final class ReflectUtil {
    private ReflectUtil() {
    }

    public static Class<?> forName(String name) throws ClassNotFoundException {
        checkPackageAccess(name);
        return Class.forName(name);
    }

    public static Object newInstance(Class<?> cls) throws InstantiationException, IllegalAccessException {
        checkPackageAccess(cls);
        return cls.newInstance();
    }

    public static void ensureMemberAccess(Class<?> currentClass, Class<?> memberClass, Object target, int modifiers) throws IllegalAccessException {
        if (target != null || !Modifier.isProtected(modifiers)) {
            Reflection.ensureMemberAccess(currentClass, memberClass, target, modifiers);
            return;
        }
        int mods = (modifiers & -5) | 1;
        Reflection.ensureMemberAccess(currentClass, memberClass, target, mods);
        try {
            Reflection.ensureMemberAccess(currentClass, memberClass, target, mods & -2);
        } catch (IllegalAccessException e) {
            if (!isSubclassOf(currentClass, memberClass)) {
                throw e;
            }
        }
    }

    private static boolean isSubclassOf(Class<?> queryClass, Class<?> ofClass) {
        while (queryClass != null) {
            if (queryClass == ofClass) {
                return true;
            }
            queryClass = queryClass.getSuperclass();
        }
        return false;
    }

    public static void checkPackageAccess(Class<?> clazz) {
        checkPackageAccess(clazz.getName());
        if (isNonPublicProxyClass(clazz)) {
            checkProxyPackageAccess(clazz);
        }
    }

    public static void checkPackageAccess(String name) {
        int b;
        SecurityManager s = System.getSecurityManager();
        if (s != null) {
            String cname = name.replace('/', '.');
            if (cname.startsWith("[") && (b = cname.lastIndexOf(91) + 2) > 1 && b < cname.length()) {
                cname = cname.substring(b);
            }
            int i = cname.lastIndexOf(46);
            if (i != -1) {
                s.checkPackageAccess(cname.substring(0, i));
            }
        }
    }

    public static boolean isPackageAccessible(Class<?> clazz) {
        try {
            checkPackageAccess(clazz);
            return true;
        } catch (SecurityException e) {
            return false;
        }
    }

    private static boolean isAncestor(ClassLoader p, ClassLoader cl) {
        ClassLoader acl = cl;
        do {
            acl = acl.getParent();
            if (p == acl) {
                return true;
            }
        } while (acl != null);
        return false;
    }

    public static boolean needsPackageAccessCheck(ClassLoader from, ClassLoader to) {
        if (from == null || from == to) {
            return false;
        }
        if (to == null) {
            return true;
        }
        return true ^ isAncestor(from, to);
    }

    public static void checkProxyPackageAccess(Class<?> clazz) {
        if (System.getSecurityManager() != null && Proxy.isProxyClass(clazz)) {
            for (Class<?> intf : clazz.getInterfaces()) {
                checkPackageAccess(intf);
            }
        }
    }

    public static void checkProxyPackageAccess(ClassLoader ccl, Class<?>... interfaces) {
        if (System.getSecurityManager() != null) {
            for (Class<?> intf : interfaces) {
                if (needsPackageAccessCheck(ccl, intf.getClassLoader())) {
                    checkPackageAccess(intf);
                }
            }
        }
    }

    public static boolean isNonPublicProxyClass(Class<?> cls) {
        String name = cls.getName();
        int i = name.lastIndexOf(46);
        String pkg = i != -1 ? name.substring(0, i) : "";
        if (!Proxy.isProxyClass(cls) || pkg.isEmpty()) {
            return false;
        }
        return true;
    }
}
