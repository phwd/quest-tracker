package X;

import androidx.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;

/* renamed from: X.0Ca  reason: invalid class name and case insensitive filesystem */
public class C00940Ca {
    public static final AnonymousClass06D<String, Class<?>> A00 = new AnonymousClass06D<>();

    /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: X.06D<java.lang.String, java.lang.Class<?>> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r0v7. Raw type applied. Possible types: java.lang.Class<?>, java.lang.Class<? extends X.0MN> */
    @NonNull
    public static Class<? extends AnonymousClass0MN> A00(@NonNull ClassLoader classLoader, @NonNull String str) {
        try {
            AnonymousClass06D<String, Class<?>> r1 = A00;
            Class<? extends AnonymousClass0MN> cls = (Class) r1.get(str);
            if (cls != null) {
                return cls;
            }
            Class cls2 = Class.forName(str, false, classLoader);
            r1.put(str, cls2);
            return cls2;
        } catch (ClassNotFoundException e) {
            throw new AnonymousClass0CN(AnonymousClass006.A07("Unable to instantiate fragment ", str, ": make sure class name exists"), e);
        } catch (ClassCastException e2) {
            throw new AnonymousClass0CN(AnonymousClass006.A07("Unable to instantiate fragment ", str, ": make sure class is a valid subclass of Fragment"), e2);
        }
    }

    @NonNull
    public AnonymousClass0MN A01(@NonNull ClassLoader classLoader, @NonNull String str) {
        Exception e;
        String str2;
        try {
            return (AnonymousClass0MN) A00(classLoader, str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException e2) {
            e = e2;
            str2 = AnonymousClass006.A07("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public");
            throw new AnonymousClass0CN(str2, e);
        } catch (NoSuchMethodException e3) {
            e = e3;
            str2 = AnonymousClass006.A07("Unable to instantiate fragment ", str, ": could not find Fragment constructor");
            throw new AnonymousClass0CN(str2, e);
        } catch (InvocationTargetException e4) {
            e = e4;
            str2 = AnonymousClass006.A07("Unable to instantiate fragment ", str, ": calling Fragment constructor caused an exception");
            throw new AnonymousClass0CN(str2, e);
        }
    }
}
