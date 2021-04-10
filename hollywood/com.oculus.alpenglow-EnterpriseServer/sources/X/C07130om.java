package X;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

/* renamed from: X.0om  reason: invalid class name and case insensitive filesystem */
public final class C07130om {
    public static Class<?> A00(Class<?> cls) {
        try {
            if (cls.getEnclosingMethod() == null && !Modifier.isStatic(cls.getModifiers())) {
                return cls.getEnclosingClass();
            }
        } catch (NullPointerException | SecurityException unused) {
        }
        return null;
    }

    public static <T> T A02(Class<T> cls, boolean z) throws IllegalArgumentException {
        Exception e;
        String str;
        Constructor<T> constructor;
        try {
            constructor = cls.getDeclaredConstructor(new Class[0]);
            if (z) {
                A06(constructor);
            } else if (!Modifier.isPublic(constructor.getModifiers())) {
                throw new IllegalArgumentException(AnonymousClass006.A07("Default constructor for ", cls.getName(), " is not accessible (non-public?): not allowed to try modify access via Reflection: can not instantiate type"));
            }
        } catch (NoSuchMethodException unused) {
            constructor = null;
        } catch (Exception e2) {
            e = e2;
            str = "Failed to find default constructor of class ";
            A05(e, AnonymousClass006.A08(str, cls.getName(), ", problem: ", e.getMessage()));
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
        if (constructor != null) {
            try {
                return constructor.newInstance(new Object[0]);
            } catch (Exception e3) {
                e = e3;
                str = "Failed to instantiate class ";
                A05(e, AnonymousClass006.A08(str, cls.getName(), ", problem: ", e.getMessage()));
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        } else {
            throw new IllegalArgumentException(AnonymousClass006.A07("Class ", cls.getName(), " has no default (no arg) constructor"));
        }
    }

    public static void A06(Member member) {
        AccessibleObject accessibleObject = (AccessibleObject) member;
        try {
            accessibleObject.setAccessible(true);
        } catch (SecurityException e) {
            if (!accessibleObject.isAccessible()) {
                Class<?> declaringClass = member.getDeclaringClass();
                throw new IllegalArgumentException("Can not access " + member + " (from class " + declaringClass.getName() + "; failed to set access: " + e.getMessage());
            }
        }
    }

    public static Class<?> A01(String str) throws ClassNotFoundException {
        if (str.indexOf(46) < 0) {
            if ("int".equals(str)) {
                return Integer.TYPE;
            }
            if ("long".equals(str)) {
                return Long.TYPE;
            }
            if ("float".equals(str)) {
                return Float.TYPE;
            }
            if ("double".equals(str)) {
                return Double.TYPE;
            }
            if ("boolean".equals(str)) {
                return Boolean.TYPE;
            }
            if ("byte".equals(str)) {
                return Byte.TYPE;
            }
            if ("char".equals(str)) {
                return Character.TYPE;
            }
            if ("short".equals(str)) {
                return Short.TYPE;
            }
            if ("void".equals(str)) {
                return Void.TYPE;
            }
        }
        Throwable e = null;
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader != null) {
            try {
                return Class.forName(str, true, contextClassLoader);
            } catch (Exception e2) {
                e = e2;
                while (e.getCause() != null) {
                    e = e.getCause();
                }
            }
        }
        try {
            return Class.forName(str);
        } catch (Exception e3) {
            e = e3;
            if (e == null) {
                while (e.getCause() != null) {
                    e = e.getCause();
                }
                e = e;
            }
            if (e instanceof RuntimeException) {
                throw e;
            }
            throw new ClassNotFoundException(e.getMessage(), e);
        }
    }

    public static String A03(Class<?> cls) {
        if (cls.isAnnotation()) {
            return "annotation";
        }
        if (cls.isArray()) {
            return "array";
        }
        if (cls.isEnum()) {
            return "enum";
        }
        if (cls.isPrimitive()) {
            return "primitive";
        }
        return null;
    }

    public static void A04(Throwable th) {
        while (th.getCause() != null) {
            th = th.getCause();
        }
        String message = th.getMessage();
        if ((th instanceof RuntimeException) || (th instanceof Error)) {
            throw th;
        }
        throw new IllegalArgumentException(message, th);
    }

    public static void A05(Throwable th, String str) {
        while (th.getCause() != null) {
            th = th.getCause();
        }
        if ((th instanceof RuntimeException) || (th instanceof Error)) {
            throw th;
        }
        throw new IllegalArgumentException(str, th);
    }
}
