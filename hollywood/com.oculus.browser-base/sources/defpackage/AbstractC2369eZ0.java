package defpackage;

import android.content.Context;
import org.chromium.base.BundleUtils;
import org.chromium.base.ContextUtils;

/* renamed from: eZ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2369eZ0 {

    /* renamed from: a  reason: collision with root package name */
    public static final C5271va f9859a = new C5271va(0);

    public static Context a(Context context) {
        if (!BundleUtils.c(context, "chrome")) {
            return context;
        }
        return BundleUtils.a(context, "chrome");
    }

    public static Object b(Context context, String str) {
        Context applicationContext = ContextUtils.getApplicationContext();
        if (applicationContext != null) {
            boolean z = false;
            try {
                Class.forName(str, false, applicationContext.getClassLoader());
                z = true;
            } catch (ClassNotFoundException unused) {
            }
            if (z) {
                context = applicationContext;
            }
        }
        try {
            return context.getClassLoader().loadClass(str).newInstance();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
