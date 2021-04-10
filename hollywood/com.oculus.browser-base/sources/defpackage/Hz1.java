package defpackage;

import android.os.SystemClock;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.EventForwarder;

/* renamed from: Hz1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Hz1 {
    public static boolean a(WebContents webContents, float f) {
        if (webContents == null) {
            return false;
        }
        EventForwarder n0 = webContents.n0();
        long uptimeMillis = SystemClock.uptimeMillis();
        n0.e(12, uptimeMillis, 0.0f);
        n0.e(13, uptimeMillis, f);
        n0.e(14, uptimeMillis, 0.0f);
        return true;
    }
}
