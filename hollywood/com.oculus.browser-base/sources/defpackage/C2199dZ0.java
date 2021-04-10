package defpackage;

import android.content.Context;
import java.util.Objects;
import org.chromium.base.BundleUtils;
import org.chromium.base.ContextUtils;

/* renamed from: dZ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2199dZ0 extends AbstractC5765yS {
    @Override // defpackage.AbstractC5765yS
    public AbstractComponentCallbacksC3550lS a(ClassLoader classLoader, String str) {
        boolean z;
        boolean z2;
        try {
            Class.forName(str, false, classLoader);
            z = true;
        } catch (ClassNotFoundException unused) {
            z = false;
        }
        if (z) {
            return super.a(classLoader, str);
        }
        C5271va vaVar = AbstractC2369eZ0.f9859a;
        Objects.requireNonNull(vaVar);
        C5101ua uaVar = new C5101ua(vaVar);
        while (uaVar.hasNext()) {
            ClassLoader classLoader2 = (ClassLoader) uaVar.next();
            try {
                Class.forName(str, false, classLoader2);
                z2 = true;
                continue;
            } catch (ClassNotFoundException unused2) {
                z2 = false;
                continue;
            }
            if (z2) {
                return super.a(classLoader2, str);
            }
        }
        Context applicationContext = ContextUtils.getApplicationContext();
        if (BundleUtils.c(applicationContext, "feedv2")) {
            classLoader = BundleUtils.a(applicationContext, "feedv2").getClassLoader();
        }
        return super.a(classLoader, str);
    }
}
