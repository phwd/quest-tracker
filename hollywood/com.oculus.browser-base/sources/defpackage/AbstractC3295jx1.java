package defpackage;

import android.content.Context;
import android.os.Build;
import java.io.File;
import org.chromium.base.ContextUtils;

/* renamed from: jx1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3295jx1 {
    public static void a() {
        PU0 pu0 = NU0.f8549a;
        int f = pu0.f("org.chromium.chrome.browser.webapps.extracted_dex_version", -1);
        int f2 = pu0.f("org.chromium.chrome.browser.webapps.last_sdk_version", -1);
        if (AbstractC1575Zv.e().g("always-extract-webapk-dex-on-startup") || f != 7 || f2 != Build.VERSION.SDK_INT) {
            pu0.n("org.chromium.chrome.browser.webapps.extracted_dex_version", 7);
            pu0.n("org.chromium.chrome.browser.webapps.last_sdk_version", Build.VERSION.SDK_INT);
            Context applicationContext = ContextUtils.getApplicationContext();
            AbstractC3375kQ.e(applicationContext.getDir("dex", 0), AbstractC3375kQ.f10275a);
            File file = new File(applicationContext.getDir("dex", 0), "webapk7.dex");
            if (AbstractC3375kQ.b(applicationContext, "webapk7.dex", file)) {
                P21 Y = P21.Y();
                try {
                    if (!KE.b(file)) {
                        Y.close();
                        return;
                    }
                    Y.close();
                    file.setReadable(true, false);
                    return;
                } catch (Throwable th) {
                    AbstractC0754Mh1.f8495a.a(th, th);
                }
            } else {
                return;
            }
        } else {
            return;
        }
        throw th;
    }
}
