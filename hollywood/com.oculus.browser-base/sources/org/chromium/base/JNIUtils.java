package org.chromium.base;

import android.content.Context;
import android.text.TextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class JNIUtils {

    /* renamed from: a  reason: collision with root package name */
    public static Boolean f10587a;
    public static ClassLoader b;

    public static ClassLoader getSplitClassLoader(String str) {
        Context applicationContext = ContextUtils.getApplicationContext();
        if (!TextUtils.isEmpty(str) && BundleUtils.c(applicationContext, str)) {
            return BundleUtils.a(applicationContext, str).getClassLoader();
        }
        ClassLoader classLoader = b;
        return classLoader == null ? JNIUtils.class.getClassLoader() : classLoader;
    }

    public static boolean isSelectiveJniRegistrationEnabled() {
        if (f10587a == null) {
            f10587a = Boolean.FALSE;
        }
        return f10587a.booleanValue();
    }
}
