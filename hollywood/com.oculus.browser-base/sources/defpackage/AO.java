package defpackage;

import android.content.Context;
import org.chromium.base.BundleUtils;

/* renamed from: AO  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AO extends QG0 {
    public static Context a(Context context) {
        C5271va vaVar = AbstractC2369eZ0.f9859a;
        if (!BundleUtils.c(context, "feedv2")) {
            return context;
        }
        ClassLoader classLoader = BundleUtils.a(context, "feedv2").getClassLoader();
        AbstractC2369eZ0.f9859a.add(classLoader);
        return new C2028cZ0(context, classLoader);
    }
}
