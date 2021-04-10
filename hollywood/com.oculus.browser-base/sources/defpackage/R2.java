package defpackage;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* renamed from: R2  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class R2 {

    /* renamed from: a  reason: collision with root package name */
    public static final Class f8805a;
    public static final Field b;
    public static final Field c;
    public static final Method d;
    public static final Method e;
    public static final Method f;
    public static final Handler g = new Handler(Looper.getMainLooper());

    /* JADX WARNING: Removed duplicated region for block: B:25:0x005d A[SYNTHETIC, Splitter:B:25:0x005d] */
    static {
        /*
        // Method dump skipped, instructions count: 178
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.R2.<clinit>():void");
    }

    public static boolean a() {
        int i = Build.VERSION.SDK_INT;
        return i == 26 || i == 27;
    }

    public static boolean b(Activity activity) {
        Object obj;
        if (Build.VERSION.SDK_INT >= 28) {
            activity.recreate();
            return true;
        } else if (a() && f == null) {
            return false;
        } else {
            if (e == null && d == null) {
                return false;
            }
            try {
                Object obj2 = c.get(activity);
                if (obj2 == null || (obj = b.get(activity)) == null) {
                    return false;
                }
                Application application = activity.getApplication();
                Q2 q2 = new Q2(activity);
                application.registerActivityLifecycleCallbacks(q2);
                Handler handler = g;
                handler.post(new N2(q2, obj2));
                try {
                    if (a()) {
                        Method method = f;
                        Boolean bool = Boolean.FALSE;
                        method.invoke(obj, obj2, null, null, 0, bool, null, null, bool, bool);
                    } else {
                        activity.recreate();
                    }
                    handler.post(new O2(application, q2));
                    return true;
                } catch (Throwable th) {
                    g.post(new O2(application, q2));
                    throw th;
                }
            } catch (Throwable unused) {
                return false;
            }
        }
    }
}
