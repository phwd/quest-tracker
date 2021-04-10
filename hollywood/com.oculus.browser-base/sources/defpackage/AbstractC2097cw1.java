package defpackage;

import android.content.Intent;
import android.os.Build;
import java.util.Collections;
import java.util.Set;
import org.chromium.chrome.browser.app.ChromeActivity;

/* renamed from: cw1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2097cw1 implements AbstractC3292jw1 {

    /* renamed from: a  reason: collision with root package name */
    public static final Set f9731a = Collections.unmodifiableSet(AbstractC0417Gv.d("G950", "N950", "G955", "G892"));
    public static final Set b = Collections.unmodifiableSet(AbstractC0417Gv.d("SC-02J", "SCV36", "SC-03J", "SCV35", "SC-01K", "SCV37"));
    public static final boolean c = (Build.VERSION.SDK_INT < 26);
    public static Boolean d;

    public boolean a() {
        int i;
        if (d == null) {
            boolean z = true;
            try {
                i = ((Integer) Class.forName("android.os.SystemProperties").getMethod("getInt", String.class, Integer.TYPE).invoke(null, "ro.boot.vr", 0)).intValue();
            } catch (Exception e) {
                AbstractC1220Ua0.a("Exception while getting system property %s. Using default %s.", "ro.boot.vr", 0, e);
                i = 0;
            }
            if (i != 1) {
                z = false;
            }
            d = Boolean.valueOf(z);
        }
        return d.booleanValue();
    }

    public abstract void b(ChromeActivity chromeActivity, Intent intent);
}
