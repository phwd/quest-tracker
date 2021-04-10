package defpackage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import org.chromium.base.ContextUtils;

/* renamed from: nz  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3983nz {

    /* renamed from: a  reason: collision with root package name */
    public static SharedPreferences f10523a;

    static {
        P21 g0 = P21.g0();
        try {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(ContextUtils.f10585a);
            g0.close();
            f10523a = defaultSharedPreferences;
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}
