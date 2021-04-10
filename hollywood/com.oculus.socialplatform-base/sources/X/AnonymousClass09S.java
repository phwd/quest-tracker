package X;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import java.lang.reflect.InvocationTargetException;

/* renamed from: X.09S  reason: invalid class name */
public class AnonymousClass09S {
    public static final C000502v<String, Class<?>> A00 = new C000502v<>();

    /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: X.02v<java.lang.String, java.lang.Class<?>> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r0v7. Raw type applied. Possible types: java.lang.Class<?>, java.lang.Class<? extends androidx.fragment.app.Fragment> */
    @NonNull
    public static Class<? extends Fragment> A00(@NonNull ClassLoader classLoader, @NonNull String str) {
        try {
            C000502v<String, Class<?>> r1 = A00;
            Class<? extends Fragment> cls = (Class) r1.get(str);
            if (cls != null) {
                return cls;
            }
            Class cls2 = Class.forName(str, false, classLoader);
            r1.put(str, cls2);
            return cls2;
        } catch (ClassNotFoundException e) {
            throw new AnonymousClass09F(AnonymousClass006.A09("Unable to instantiate fragment ", str, ": make sure class name exists"), e);
        } catch (ClassCastException e2) {
            throw new AnonymousClass09F(AnonymousClass006.A09("Unable to instantiate fragment ", str, ": make sure class is a valid subclass of Fragment"), e2);
        }
    }

    @NonNull
    public Fragment A01(@NonNull ClassLoader classLoader, @NonNull String str) {
        try {
            return (Fragment) A00(classLoader, str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (InstantiationException e) {
            throw new AnonymousClass09F(AnonymousClass006.A09("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e);
        } catch (IllegalAccessException e2) {
            throw new AnonymousClass09F(AnonymousClass006.A09("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e2);
        } catch (NoSuchMethodException e3) {
            throw new AnonymousClass09F(AnonymousClass006.A09("Unable to instantiate fragment ", str, ": could not find Fragment constructor"), e3);
        } catch (InvocationTargetException e4) {
            throw new AnonymousClass09F(AnonymousClass006.A09("Unable to instantiate fragment ", str, ": calling Fragment constructor caused an exception"), e4);
        }
    }
}
