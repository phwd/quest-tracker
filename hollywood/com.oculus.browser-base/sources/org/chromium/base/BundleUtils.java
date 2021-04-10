package org.chromium.base;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.PathClassLoader;
import java.lang.reflect.Field;
import java.util.Arrays;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class BundleUtils {

    /* renamed from: a  reason: collision with root package name */
    public static Boolean f10583a;
    public static final Object b = new Object();
    public static final BW0 c = new BW0();

    public static Context a(Context context, String str) {
        boolean z;
        boolean z2;
        boolean z3;
        Context context2;
        if (Build.VERSION.SDK_INT < 26) {
            return context;
        }
        Context context3 = context;
        while (true) {
            try {
                z = false;
                z2 = true;
                if (!(context3 instanceof ContextWrapper)) {
                    z3 = false;
                    break;
                } else if (context3 instanceof Application) {
                    z3 = true;
                    break;
                } else {
                    context3 = ((ContextWrapper) context3).getBaseContext();
                }
            } catch (PackageManager.NameNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        if (z3) {
            context2 = C3837n7.b(context, str);
        } else {
            synchronized (b) {
                context2 = C3837n7.b(context, str);
            }
        }
        ClassLoader parent = context2.getClassLoader().getParent();
        Context applicationContext = ContextUtils.getApplicationContext();
        boolean z4 = isolatedSplitsEnabled() && !parent.equals(BundleUtils.class.getClassLoader()) && applicationContext != null && !parent.equals(applicationContext.getClassLoader());
        BW0 bw0 = c;
        synchronized (bw0) {
            if (z4) {
                if (bw0.e(str) >= 0) {
                    z = true;
                }
                if (!z) {
                    bw0.put(str, new PathClassLoader(context2.getApplicationInfo().splitSourceDirs[Arrays.binarySearch(C3837n7.c(context2.getApplicationInfo()), str)], applicationContext.getClassLoader()));
                }
            }
            ClassLoader classLoader = (ClassLoader) bw0.getOrDefault(str, null);
            if (classLoader == null) {
                bw0.put(str, context2.getClassLoader());
            } else if (!classLoader.equals(context2.getClassLoader())) {
                Context context4 = context2;
                while (context4 instanceof ContextWrapper) {
                    context4 = ((ContextWrapper) context4).getBaseContext();
                }
                try {
                    Field declaredField = context4.getClass().getDeclaredField("mClassLoader");
                    declaredField.setAccessible(true);
                    declaredField.set(context4, classLoader);
                } catch (ReflectiveOperationException e2) {
                    throw new RuntimeException("Error setting ClassLoader.", e2);
                }
            }
            z2 = z4;
        }
        AbstractC3100ip1.f10165a.a("Android.IsolatedSplits.ClassLoaderReplaced." + str, z2);
        return context2;
    }

    public static String b(String str, String str2) {
        ApplicationInfo applicationInfo;
        String[] c2;
        int binarySearch;
        if (Build.VERSION.SDK_INT < 26 || (c2 = C3837n7.c((applicationInfo = ContextUtils.getApplicationContext().getApplicationInfo()))) == null || (binarySearch = Arrays.binarySearch(c2, str2)) < 0) {
            return null;
        }
        try {
            return applicationInfo.splitSourceDirs[binarySearch] + "!/lib/" + ((String) applicationInfo.getClass().getField("primaryCpuAbi").get(applicationInfo)) + "/" + System.mapLibraryName(str);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean c(Context context, String str) {
        String[] c2;
        if (Build.VERSION.SDK_INT >= 26 && (c2 = C3837n7.c(context.getApplicationInfo())) != null && Arrays.asList(c2).contains(str)) {
            return true;
        }
        return false;
    }

    public static String getNativeLibraryPath(String str, String str2) {
        P21 f0 = P21.f0();
        try {
            String findLibrary = ((BaseDexClassLoader) BundleUtils.class.getClassLoader()).findLibrary(str);
            if (findLibrary != null) {
                f0.close();
                return findLibrary;
            }
            String findLibrary2 = ((BaseDexClassLoader) ContextUtils.getApplicationContext().getClassLoader()).findLibrary(str);
            if (findLibrary2 != null) {
                f0.close();
                return findLibrary2;
            }
            String b2 = b(str, str2);
            f0.close();
            return b2;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public static boolean isBundleForNative() {
        return false;
    }

    public static boolean isolatedSplitsEnabled() {
        return false;
    }
}
