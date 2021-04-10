package defpackage;

import android.content.SharedPreferences;
import org.chromium.base.ContextUtils;

/* renamed from: FM0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class FM0 {
    public static SharedPreferences a() {
        P21 f0 = P21.f0();
        try {
            SharedPreferences sharedPreferences = ContextUtils.getApplicationContext().getSharedPreferences("start_surface", 0);
            f0.close();
            return sharedPreferences;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}
