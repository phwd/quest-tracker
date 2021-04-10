package org.chromium.base;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.os.Build;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ContextUtils {

    /* renamed from: a  reason: collision with root package name */
    public static Context f10585a;

    public static Activity a(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    public static AssetManager b() {
        Context applicationContext = getApplicationContext();
        while (applicationContext instanceof ContextWrapper) {
            applicationContext = ((ContextWrapper) applicationContext).getBaseContext();
        }
        return applicationContext.getAssets();
    }

    public static String c() {
        if (Build.VERSION.SDK_INT >= 28) {
            return C2812h7.b();
        }
        try {
            return (String) Class.forName("android.app.ActivityThread").getMethod("currentProcessName", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Context getApplicationContext() {
        return f10585a;
    }
}
